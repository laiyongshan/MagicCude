package com.rflash.magiccube.ui.presentoperation.repay;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.presentoperation.AdapterItemOperation;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.PresentOperationActivity;
import com.rflash.magiccube.ui.presentoperation.PresentOperationAdapter;
import com.rflash.magiccube.ui.repaymodify.RepayModifyActivity;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */

public class RepayFragment extends MVPBaseFragment<RepayContract.View, RepayPresenter> implements RepayContract.View, AdapterItemOperation, LoadRecyclerView.OnLoadMoreListener, DefaultView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    //还款提示弹框
    private NormalDialog repayDialog;
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
    public final String TRANTYPE = Config.REPAY;

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

    public static final String MODIFY_KEY = "repay";
    //修改requestCode
    public static final int MODIFY_REQUESTCODE = 0x4444;

    public static RepayFragment newInstance() {

        return new RepayFragment();
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

    @Override
    protected int getLayout() {
        return R.layout.fragment_recycleview;
    }


    /**
     * 还款
     *
     * @param operationItem
     */
    @Override
    public void operation(OperationItem operationItem) {
        if (repayDialog == null) {
            repayDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_repay);
        }
        String moneyYuan = StringUtil.getTwoPointString(operationItem.getAmt());
        repayDialog.show();
        ((TextView) repayDialog.findViewById(R.id.tv_money)).setText(context.getResources().getString(R.string.money, moneyYuan));
        TextView tvCancel = repayDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(v -> repayDialog.dismiss());
        TextView tvSure = repayDialog.findViewById(R.id.tv_sure);
        tvSure.setTag(R.id.tv_one_tag, operationItem.getPlanId());
        tvSure.setOnClickListener(v -> {
            String plantId = (String) v.getTag(R.id.tv_one_tag);
            repayDialog.dismiss();
            mPresenter.confirmRepay(plantId);
        });

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
            //跳到修改界面
            modifyDialog.dismiss();
            //跳到修改界面
            Intent intent = new Intent(context, RepayModifyActivity.class);
            intent.putExtra(MODIFY_KEY, operationItem);
            RepayFragment.this.startActivityForResult(intent, MODIFY_REQUESTCODE);
        });
    }


    /**
     * 查询还款数据成功
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
     * 还款成功
     */
    @Override
    public void repaySuccess() {
        Toast.makeText(getContext(), "确认还款成功", Toast.LENGTH_SHORT).show();
        onRefresh();
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
     * 条件搜索
     */
    public void search() {
        onRefresh();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case RepayModifyActivity.MODIFY_RESULT_CODE:// 修改
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


}
