package com.rflash.magiccube.ui.presentoperation.consume;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.consumemodify.ConsumeModifyActivity;
import com.rflash.magiccube.ui.presentoperation.AdapterItemOperation;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.OperationTime;
import com.rflash.magiccube.ui.presentoperation.PresentOperationActivity;
import com.rflash.magiccube.ui.presentoperation.PresentOperationAdapter;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 */

public class ConsumeFragment extends MVPBaseFragment<ConsumeContract.View, ConsumePresenter> implements ConsumeContract.View, AdapterItemOperation, SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadMoreListener, DefaultView {

    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    //一个小时内连续消费提示
    private NormalDialog consumeDialog;
    //  网络请求 dialog
    private ProgressDialog netDialog;
    //修改规则dialog
    private NormalDialog modifyDialog;


    //数据源
    private List<OperationItem> list = new ArrayList<>();

    //adapter
    private PresentOperationAdapter mAdapter;

    @BindView(R.id.iv_nodata)
    ImageView ivNodata;

    //当前page
    private int pageNum = 1;


    // 操作类型 SALE：消费  REPAY：还款
    public final String TRANTYPE = Config.SALE;

    //今天
    public final String TODAY = "1";


    //状态 NOT_OPERATOR：未操作    DEAL：已操作
    public String STATE = Config.NOT_OPERATOR + "," + Config.DEAL;

    //卡位
    public String CARDNO = "";


    //是否是  下拉刷新
    public boolean mRefresh = false;

    //总页数
    private int mTotalPage;

    // 消费requestCode
    public static final int SALE_REQUESTCODE = 0x2222;
    //修改requestCode
    public static final int MODIFY_REQUESTCODE = 0x3333;

    public static final String MODIFY_KEY = "consume";


    public static ConsumeFragment newInstance() {


        return new ConsumeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recycleview;
    }

    @Override
    protected void initView() {

        //设置swipeRefreshLayout  下拉时圈圈的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#474E7A"));
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //创建OperationRecycleAdapter
        mAdapter = new PresentOperationAdapter(list, getContext());
        //设置LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置上拉监听
        recyclerView.setOnLoadMoreListener(this);
        //添加空白界面 添加到根布局
        recyclerView.addEmptyView(ivNodata);
        //设置默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //设置adapter
        recyclerView.setAdapter(mAdapter);
        //添加点击消费 还款监听
        mAdapter.setConsumeClick(this);
        //请求数据
        mPresenter.planQuery(STATE, TRANTYPE, TODAY, pageNum + "", CARDNO);

    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mRefresh = true;
        pageNum = 1;
        //请求数据
        mPresenter.planQuery(STATE, TRANTYPE, TODAY, pageNum + "", CARDNO);

    }


    /**
     * 上拉加载
     */
    @Override
    public void onLoad() {

        if (mTotalPage >= pageNum + 1) {
            //请求数据
            pageNum++;
            mPresenter.planQuery(STATE, TRANTYPE, TODAY, pageNum + "", CARDNO);
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(context, "没有更多数据啦", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 查询消费数据成功
     *
     * @param operation
     */
    @Override
    public void querySuccess(Operation operation) {

        List<OperationItem> result = operation.getResult();
        mTotalPage = operation.getTotalPage();
        if (result != null) {
            if (mRefresh) {
                list.clear();
            }
            list.addAll(result);
            mAdapter.notifyDataSetChanged();
        }
        mRefresh = false;
    }

    /**
     * 校验交易时间
     *
     * @param operationTime
     */
    @Override
    public void lastOperator(OperationTime operationTime, OperationItem operationItem) {
        if("FAIL".equals(operationTime.getVerifyCheck())){
            Toast.makeText(context,"该卡片未通过验证，不允许交易，请联系管理员！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (operationTime.getTimeCheck().equals(Config.OPERATIONTIME_SUCCESS)) {
            consume(operationItem);
        } else if (operationTime.getTimeCheck().equals(Config.OPERATIONTIME_FAIL)) {
            //弹窗
            if (consumeDialog == null) {
                consumeDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_repay);
            }

            consumeDialog.show();
            ((TextView) consumeDialog.findViewById(R.id.tv_title)).setText("消费确认");
            ((TextView) consumeDialog.findViewById(R.id.tv_text_sure)).setText(operationTime.getMinTranInterval() + "分钟内有消费记录，确认消费吗");
            ((TextView) consumeDialog.findViewById(R.id.tv_money)).setText("卡号：" + StringUtil.getBankNo(operationItem.getCardNo()));

            TextView tvCancel = consumeDialog.findViewById(R.id.tv_cancel);
            tvCancel.setOnClickListener(v -> consumeDialog.dismiss());
            TextView tvSure = consumeDialog.findViewById(R.id.tv_sure);
            tvSure.setTag(R.id.tv_one_tag, operationItem);
            tvSure.setOnClickListener(v -> {
                OperationItem operationItem1 = (OperationItem) v.getTag(R.id.tv_one_tag);
                consumeDialog.dismiss();
                consume(operationItem1);
            });

        }
    }

    /**
     * 去pos机消费
     *
     * @param operationItem
     */
    public void consume(OperationItem operationItem) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Config.APPLICATIONID, Config.SALE_DIR));
        intent.putExtra("amount", operationItem.getAmt());
        intent.putExtra("mode", 1);
        startActivityForResult(intent, SALE_REQUESTCODE);
    }


    /**
     * 消费  查询是否一个小时内消费
     *
     * @param operationItem
     */
    @Override
    public void operation(OperationItem operationItem) {
        if("freeze".equals(operationItem.getChannelState()) || "N".equals(operationItem.getMerchantBind())
                || "freeze".equals(operationItem.getMerchantState())){
            Toast.makeText(context,"商户状态不正常或未下载该商户",Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.lastOperator(operationItem);
    }


    /**
     * 只选中这张卡
     *
     * @param operationItem
     */
    @Override
    public void selectTheCard(OperationItem operationItem) {
        mRefresh = true;
        pageNum = 1;
        CARDNO = operationItem.getCardNo();
        mPresenter.planQuery(STATE, TRANTYPE, TODAY, pageNum + "", CARDNO);
        String bankNum = operationItem.getCardNo();
        String subBankNum = bankNum.substring(bankNum.length() - 4, bankNum.length());
        ((PresentOperationActivity) context).selectTheCard(subBankNum);
    }


    /**
     * 长按修改
     *
     * @param operationItem
     */
    @Override
    public void longClickItem(OperationItem operationItem) {
        if (modifyDialog == null) {
            modifyDialog = new NormalDialog(context, R.style.bottom_dialog, R.layout.dialog_modify);
        }
        modifyDialog.show();
        View tvModify = modifyDialog.findViewById(R.id.tv_modify);
        tvModify.setOnClickListener(v -> {
            modifyDialog.dismiss();
            //跳到修改界面
            Intent intent = new Intent(context, ConsumeModifyActivity.class);
            intent.putExtra(MODIFY_KEY, operationItem);
            ConsumeFragment.this.startActivityForResult(intent, MODIFY_REQUESTCODE);
        });
    }

    /**
     * 条件搜索
     */
    public void search() {
        onRefresh();
    }


    /**
     * 链接pos  消费返回的处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_CANCELED://
                if (requestCode == SALE_REQUESTCODE) {
                    String reason = data.getStringExtra("reason");
                    if (reason != null) {
                        Toast.makeText(context, reason, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case Activity.RESULT_OK://消费成功
                if (requestCode == SALE_REQUESTCODE) {
                    Toast.makeText(context, "消费成功", Toast.LENGTH_SHORT).show();
                    onRefresh();
                }
            case ConsumeModifyActivity.MODIFY_RESULT_CODE:// 消费修改
                if (requestCode == MODIFY_REQUESTCODE) {
                    onRefresh();
                }
                break;
        }
    }


    //网络请求显示Progress
    @Override
    public void showProgress() {
        if (netDialog == null) {
            netDialog = new ProgressDialog(getContext());
            netDialog.setCancelable(true);
            netDialog.setCanceledOnTouchOutside(false);
            netDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            netDialog.setMessage("加载中...");
        }

        netDialog.show();

    }

    //网络请求隐藏Progress
    @Override

    public void dismissProgress() {
        if (netDialog != null && netDialog.isShowing()) {
            netDialog.dismiss();
        }
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.onStopLoad();
    }

    //网络请求Progress 是否隐藏
    @Override
    public boolean isDismiss() {
        return !netDialog.isShowing();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (netDialog != null) {
            if (netDialog.isShowing()) {
                netDialog.dismiss();
            }
            netDialog = null;
        }

        if (consumeDialog != null) {
            if (consumeDialog.isShowing()) {
                consumeDialog.dismiss();
            }
            consumeDialog = null;
        }

        if (modifyDialog != null) {
            if (modifyDialog.isShowing()) {
                modifyDialog.dismiss();
            }
            modifyDialog = null;
        }
    }
}
