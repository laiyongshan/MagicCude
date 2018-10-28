package com.rflash.magiccube.ui.operationtoday.unoperation;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.operationtoday.ConsumeClick;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.operationtoday.OperationAdapter;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.operationtoday.OperationTodayActivity;
import com.rflash.magiccube.ui.presentoperation.OperationTime;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 未操作
 */

public class UnOperationFragment extends MVPBaseFragment<UnOperationContract.View, UnOperationPresenter> implements UnOperationContract.View, SwipeRefreshLayout.OnRefreshListener, ConsumeClick, LoadRecyclerView.OnLoadMoreListener, DefaultView {


    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    //还款提示弹框
    private NormalDialog repayDialog;


    @BindView(R.id.iv_nodata)
    ImageView ivNodata;


    private OperationAdapter adapter;

    // 选择操作的弹框
    private NormalDialog floatingDialog;
    // 确定消费的弹窗
    private NormalDialog consumeDialog;


    private static final int SALE_REQUESTCODE = 1;

    private List<OperationItem> list = new ArrayList<>();


    //是否是  下拉刷新
    public boolean mRefresh = false;
    //总页数
    private int totalPage;
    //是否选中还款
    private boolean repay;
    //是否选中消费
    private boolean consume;
    //是否点击弹窗框
    private boolean clickCb;

    //当前页码
    public int pageNum = 1;

    public String cardNum;

    public boolean clickItem;

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
        //添加空白界面 添加到根布局
        recyclerView.addEmptyView(ivNodata);
        //设置默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //设置adapter
        recyclerView.setAdapter(adapter);
        //添加点击消费 还款监听
        adapter.setConsumeClick(this);
        //请求数据
        mPresenter.planQuery(Config.NOT_OPERATOR, "", "1", pageNum + "", "");
    }


    @Override
    protected int getLayout() {
        return R.layout.only_recycleview;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mRefresh = true;
        pageNum = 1;
        if (!clickItem) {
            mPresenter.planQuery(Config.NOT_OPERATOR, "", "1", "1", "");
        } else {
            mPresenter.planQuery(Config.NOT_OPERATOR, "", "1", "1", cardNum);
        }
    }


    /**
     * 悬停按钮点击
     */
    @OnClick(R.id.iv_floating)
    public void onClickView() {

        if (floatingDialog == null) {
            floatingDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_type);
        }

        floatingDialog.show();
        TextView tvCancel = floatingDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingDialog.dismiss();
            }
        });
        TextView tvSure = floatingDialog.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingDialog.dismiss();
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
                mPresenter.planQuery(Config.NOT_OPERATOR, tranType, "1", "1", cardNum);
            }
        });
        CheckBox cbConsume = floatingDialog.findViewById(R.id.cb_consume);
        cbConsume.setChecked(false);
        cbConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consume = ((CheckBox) v).isChecked();
            }
        });
        consume = false;
        View rlConsume = floatingDialog.findViewById(R.id.rl_consume);
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

        CheckBox cbRepay = floatingDialog.findViewById(R.id.cb_repay);
        cbRepay.setChecked(false);
        cbRepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repay = ((CheckBox) v).isChecked();
            }
        });
        repay = false;
        View rlRepay = floatingDialog.findViewById(R.id.rl_repay);
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

    /**
     * 点击消费
     *
     * @param operationItem
     */
    @Override
    public void onConsumeClick(OperationItem operationItem, int position) {
        mPresenter.lastOperator(operationItem);
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
     * 点击还款
     *
     * @param operationItem
     */
    @Override
    public void onRepay(OperationItem operationItem, int position) {
        if (repayDialog == null) {
            repayDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_repay);
        }
        String moneyYuan = StringUtil.getTwoPointString(operationItem.getAmt());
        repayDialog.show();
        ((TextView) repayDialog.findViewById(R.id.tv_money)).setText(context.getResources().getString(R.string.money, moneyYuan));
        TextView tvCancel = repayDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repayDialog.dismiss();
            }
        });
        TextView tvSure = repayDialog.findViewById(R.id.tv_sure);
        tvSure.setTag(R.id.tv_one_tag, operationItem.getPlanId());
        tvSure.setTag(R.id.tv_two_tag, position);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantId = (String) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                repayDialog.dismiss();
                mPresenter.confirm(plantId, position);
            }
        });


    }

    /**
     * 点击整个item
     *
     * @param operationItem
     */
    @Override
    public void onContentClick(OperationItem operationItem) {
        if (clickItem) {
            return;
        }
        clickItem = true;
        mRefresh = true;
        cardNum = operationItem.getCardNo();
        mPresenter.planQuery(Config.NOT_OPERATOR, "", "1", "1", cardNum);
        ((OperationTodayActivity) context).change(Config.NOT_OPERATOR);
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
                String reason = data.getStringExtra("reason");
                if (reason != null) {
                    Toast.makeText(context, reason, Toast.LENGTH_SHORT).show();
                }
                break;
            case Activity.RESULT_OK://消费成功
                if (requestCode == SALE_REQUESTCODE) {
                    Toast.makeText(context, "消费成功", Toast.LENGTH_SHORT).show();
                    onRefresh();
                }
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (floatingDialog != null) {
            if (floatingDialog.isShowing()) {
                floatingDialog.dismiss();
            }
            floatingDialog = null;
        }

        if (repayDialog != null) {
            if (repayDialog.isShowing()) {
                repayDialog.dismiss();
            }
            repayDialog = null;
        }
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }

        if (consumeDialog != null) {
            if (consumeDialog.isShowing()) {
                consumeDialog.dismiss();
            }
            consumeDialog = null;
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

            if (clickItem) {
                mPresenter.planQuery(Config.NOT_OPERATOR, tranType, "1", ++pageNum + "", cardNum);
                return;
            }
            mPresenter.planQuery(Config.NOT_OPERATOR, tranType, "1", ++pageNum + "", "");
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
            if (mRefresh || clickCb) {//刷新的或者点击弹窗框确定的请求就清空集合
                list.clear();
            }
            list.addAll(result);
        }
        adapter.notifyDataSetChanged();
        mRefresh = false;
        clickCb = false;
    }

    /**
     * 还款成功
     *
     * @param position
     */
    @Override
    public void repaySuccess(int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
        ((OperationTodayActivity) context).refresh();
    }

    /**
     * 校验交易时间
     *
     * @param operationTime
     */
    @Override
    public void lastOperator(OperationTime operationTime, OperationItem operationItem) {
        if (operationTime.getTimeCheck().equals(Config.OPERATIONTIME_SUCCESS)) {
            consume(operationItem);
        } else if (operationTime.getTimeCheck().equals(Config.OPERATIONTIME_FAIL)) {
            //弹窗
            if (consumeDialog == null) {
                consumeDialog = new NormalDialog(context, R.style.ios_bottom_dialog, R.layout.dialog_repay);
            }

            consumeDialog.show();
            ((TextView) consumeDialog.findViewById(R.id.tv_title)).setText("消费确认");
            ((TextView) consumeDialog.findViewById(R.id.tv_text_sure)).setText(operationTime.getMinTranInterval()+"分钟内有消费记录，确认消费吗");
            ((TextView) consumeDialog.findViewById(R.id.tv_money)).setText("卡号：" + StringUtil.getBankNo(operationItem.getCardNo()));

            TextView tvCancel = consumeDialog.findViewById(R.id.tv_cancel);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    consumeDialog.dismiss();
                }
            });
            TextView tvSure = consumeDialog.findViewById(R.id.tv_sure);
            tvSure.setTag(R.id.tv_one_tag, operationItem);
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag(R.id.tv_one_tag);
                    consumeDialog.dismiss();
                    consume(operationItem);
                }
            });

        }
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
}

