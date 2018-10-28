package com.rflash.magiccube.ui.operationtoday.operation;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.operationtoday.AdapterItemClick;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.operationtoday.OperationAdapter;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.operationtoday.OperationTodayActivity;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已操作
 */

public class OperationFragment extends MVPBaseFragment<OperationContract.View, OperationPresenter> implements OperationContract.View, SwipeRefreshLayout.OnRefreshListener, LoadRecyclerView.OnLoadMoreListener, DefaultView, AdapterItemClick {

    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.iv_nodata)
    ImageView ivNodata;


    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private OperationAdapter adapter;

    private NormalDialog normalDialog;

    //当前页码
    public int pageNum = 1;

    private List<OperationItem> list = new ArrayList<>();

    //是否是  下拉刷新
    public boolean refresh = false;
    //总页数
    private int totalPage;
    //是否选中还款
    private boolean repay;
    //是否选中消费
    private boolean consume;
    //是否点击弹窗框
    private boolean clickCb;

    //是否点击item
    public boolean clickItem;

    public String cardNum;

    @Override
    protected void initView() {

        //设置swipeRefreshLayout  下拉时圈圈的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#474E7A"));
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //创建OperationRecycleAdapter
        adapter = new OperationAdapter(list, getContext());
        //设置LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置上拉监听
        recyclerView.setOnLoadMoreListener(this);
        //设置默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //添加空白界面 添加到根布局
        recyclerView.addEmptyView(ivNodata);
        //设置adapter
        recyclerView.setAdapter(adapter);
        adapter.setConsumeClick(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        //请求数据
        onRefresh();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.only_recycleview;
    }

    /**
     * swipeRefreshLayout  下拉监听
     */
    @Override
    public void onRefresh() {

        refresh = true;
        pageNum = 1;
        if (!clickItem) {
            mPresenter.planQuery(Config.DEAL, "", "1", "1", "");

        } else {//点击了  卡片 然后刷新
            mPresenter.planQuery(Config.DEAL, "", "1", "1", cardNum);
        }
    }

    /**
     * 点击选中按钮
     */
    @OnClick(R.id.iv_floating)
    public void onFloatingClick() {

        if (normalDialog == null) {
            normalDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_type);
        }
        normalDialog.show();
        TextView tvCancel = normalDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalDialog.dismiss();
            }
        });
        TextView tvSure = normalDialog.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalDialog.dismiss();
                Log.i("---checked", "consume :" + consume + "  repay:" + repay);
                String tranType = "";
                if (consume && !repay) {
                    tranType = Config.SALE;
                    clickCb = true;
                } else if (!consume && repay) {
                    tranType = Config.REPAY;
                    clickCb = true;
                } else if (consume && repay) {
                    tranType = "";
                    clickCb = true;
                } else {
                    return;
                }
                pageNum = 1;
                mPresenter.planQuery(Config.DEAL, tranType, "1", "1", cardNum);
            }
        });

        CheckBox cbConsume = normalDialog.findViewById(R.id.cb_consume);
        cbConsume.setChecked(false);
        consume = false;
        cbConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consume = ((CheckBox) v).isChecked();
            }
        });
        View rlConsume = normalDialog.findViewById(R.id.rl_consume);
        rlConsume.setTag(cbConsume);
        rlConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cbConsume = (CheckBox) v.getTag();
                boolean isChecked = !cbConsume.isChecked();
                cbConsume.setChecked(isChecked);
                consume = isChecked;
            }
        });


        CheckBox cbRepay = normalDialog.findViewById(R.id.cb_repay);
        cbRepay.setChecked(false);
        cbRepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repay = ((CheckBox) v).isChecked();
            }
        });
        repay = false;
        View rlRepay = normalDialog.findViewById(R.id.rl_repay);
        rlRepay.setTag(cbRepay);
        rlRepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cbRepay = (CheckBox) v.getTag();
                boolean isChecked = !cbRepay.isChecked();
                cbRepay.setChecked(isChecked);
                repay = isChecked;
            }
        });


    }


    @Override
    public void onPause() {
        super.onPause();
        if (normalDialog != null) {
            if (normalDialog.isShowing()) {
                normalDialog.dismiss();
            }
            normalDialog = null;
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * 上拉
     */
    @Override
    public void onLoad() {

        String tranType = "";
        if (totalPage >= pageNum + 1) {

            if (consume && !repay) {
                tranType = Config.SALE;
            } else if (!consume && repay) {
                tranType = Config.REPAY;
            }
            if (clickItem){
                mPresenter.planQuery(Config.DEAL, tranType, "1", ++pageNum + "", cardNum);
                return;
            }
            mPresenter.planQuery(Config.DEAL, tranType, "1", ++pageNum + "", "");
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(context, "没有更多数据啦", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取操作数据
     *
     * @param operation
     */
    @Override
    public void getOperationItem(Operation operation) {
        List<OperationItem> result = operation.getResult();
        totalPage = operation.getTotalPage();

        if (result != null) {

            if (refresh || clickCb) {//刷新的或者点击弹窗框确定的请求就清空集合
                list.clear();
            }

            list.addAll(result);
        }
        adapter.notifyDataSetChanged();
        refresh = false;
        clickCb = false;
    }

    //  进度条
    private ProgressDialog dialog;

    @Override
    public void showProgress() {
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("加载中...");
        }

        dialog.show();

    }

    @Override
    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.onStopLoad();

    }

    @Override
    public boolean isDismiss() {
        return !dialog.isShowing();
    }

    /**
     * item点击
     *
     * @param operationItem
     */
    @Override
    public void onContentClick(OperationItem operationItem) {
        if (clickItem) {
            return;
        }
        clickItem = true;
        refresh = true;
        cardNum = operationItem.getCardNo();
        mPresenter.planQuery(Config.DEAL, "", "1", "1", cardNum);
        ((OperationTodayActivity) context).change(Config.DEAL);
    }
}
