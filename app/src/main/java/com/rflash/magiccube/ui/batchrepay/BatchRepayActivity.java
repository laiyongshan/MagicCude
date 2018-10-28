package com.rflash.magiccube.ui.batchrepay;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.operationrecord.OperationRecordActivity;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 批量还款界面
 */

public class BatchRepayActivity extends MVPBaseActivity<BatchRepayContract.View, BatchRepayPresenter> implements BatchRepayContract.View, BatchOperationListener, DatePickerDialog.OnDateSetListener, LoadRecyclerView.OnLoadMoreListener, View.OnClickListener {


    //底部已选数量和金额
    @BindView(R.id.tv_all)
    TextView tvAll;

    //底部已选数量
    private int checkNum = 0;

    //底部选中金额
    private float money = 0f;

    //当前页数
    private int pageNum = 1;

    //日期选择器dialog
    private DatePickerDialog datePickerDialog;

    //是否选中起始时间按钮
    private boolean startDateClick = false;

    //是否全选
    private boolean isSelectorAll = false;

    private NormalDialog dialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;
    //总页数
    private int totalPage;

    //开始时间
//    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    //结束时间
//    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

    //全选按钮
//    @BindView(R.id.tv_selector_all)
    TextView tvSelectorAll;

    //金额
//    @BindView(R.id.tv_money)
    TextView tvMoney;

    //代付账户
//    @BindView(R.id.tv_account)
    TextView tvAccount;

    @BindView(R.id.iv_nodata)
    ImageView ivNoData;

    //    @BindView(R.id.edt_bankcard)
    EditText edtBankCard;

    //    @BindView(R.id.edt_num)
    EditText edtNum;


    private View headView;

    @BindView(R.id.iv_go_top)
    ImageView ivGoTop;


    //是否是点击查询或者重置
    private boolean isQuery;

    private List<BatchRepay.ResultBean> list = new ArrayList<>();

    private BatchRepayAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_repay);
        initView();

        headView = LayoutInflater.from(this).inflate(R.layout.layout_batch_head, recyclerView, false);
        tvStartDate = headView.findViewById(R.id.tv_start_date);
        tvEndDate = headView.findViewById(R.id.tv_end_date);
        tvSelectorAll = headView.findViewById(R.id.tv_selector_all);
        tvMoney = headView.findViewById(R.id.tv_money);
        tvAccount = headView.findViewById(R.id.tv_account);
        edtBankCard = headView.findViewById(R.id.edt_bankcard);
        edtNum = headView.findViewById(R.id.edt_num);
        headView.findViewById(R.id.tv_refresh).setOnClickListener(this);
        tvSelectorAll.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        tvStartDate.setOnClickListener(this);
        headView.findViewById(R.id.tv_query).setOnClickListener(this);
        headView.findViewById(R.id.tv_reset).setOnClickListener(this);
        recyclerView.addHeaderView(headView);

    }

    private void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatchRepayActivity.this.finish();
            }
        });

        adapter = new BatchRepayAdapter(this, list, R.layout.layout_batch_repay);
        adapter.setListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addEmptyView(ivNoData);
        //设置默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        //设置上拉监听
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//手指向上滚动
                    ivGoTop.setVisibility(View.VISIBLE);
                } else {
                    ivGoTop.setVisibility(View.GONE);
                }
            }
        });
        mPresenter.balance();
        mPresenter.planQuery("", "", "", "", Config.NOT_OPERATOR, Config.REPAY, "1", "1");
    }


    /**
     * 列表中还款点击
     *
     * @param position 下标
     */
    @Override
    public void onRepay(int position) {
        BatchRepay.ResultBean batchRepay = list.get(position);
        if (dialog == null) {
            dialog = new NormalDialog(this, R.style.ios_bottom_dialog, R.layout.dialog_repay);
        }
        dialog.show();

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        tvTitle.setText("还款确认");

        TextView tvMoney = dialog.findViewById(R.id.tv_money);
        tvMoney.setText("笔数：1 金额：¥" + StringUtil.getTwoPointString(batchRepay.getAmt()));

        TextView tvTextSure = dialog.findViewById(R.id.tv_text_sure);
        tvTextSure.setText("确认还款吗");

        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tvSure = dialog.findViewById(R.id.tv_sure);
        tvSure.setTag(R.id.tv_one_tag, batchRepay);
        tvSure.setTag(R.id.tv_two_tag, position);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                BatchRepay.ResultBean batchRepay = (BatchRepay.ResultBean) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                mPresenter.confirm(batchRepay.getPlanId(), position);
            }
        });


    }

    /**
     * 列表中代付点击
     *
     * @param position
     */
    @Override
    public void onHelpPay(int position) {

        final BatchRepay.ResultBean batchRepay = list.get(position);
        if (dialog == null) {
            dialog = new NormalDialog(this, R.style.ios_bottom_dialog, R.layout.dialog_repay);
        }
        dialog.show();

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        tvTitle.setText("代付确认");

        TextView tvMoney = dialog.findViewById(R.id.tv_money);
        tvMoney.setText("笔数：1 金额：¥" + StringUtil.getTwoPointString(batchRepay.getAmt()));

        TextView tvTextSure = dialog.findViewById(R.id.tv_text_sure);
        tvTextSure.setText("确认代付吗");

        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tvSure = dialog.findViewById(R.id.tv_sure);
        tvSure.setTag(R.id.tv_one_tag, batchRepay);
        tvSure.setTag(R.id.tv_two_tag, position);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                BatchRepay.ResultBean batchRepay = (BatchRepay.ResultBean) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                ArrayList<Integer> positions = new ArrayList<>();
                positions.add(position);
                mPresenter.helpPay(batchRepay.getPlanId(), positions);
            }
        });
    }

    /**
     * 列表中checkbox监听
     *
     * @param position
     * @param checked
     */
    @Override
    public void onCheckListener(int position, boolean checked) {
        BatchRepay.ResultBean batchRepay = list.get(position);
        float m = Float.valueOf(batchRepay.getAmt());
        if (checked) {//选中
            checkNum++;
            money += m;
        } else {//不选中
            checkNum--;
            money -= m;
        }

        tvAll.setText(getResources().getString(R.string.bottom, checkNum, StringUtil.getTwoPointString(money)));
    }


    /**
     * 底部代付点击
     */
    @OnClick(R.id.tv_help_pay)
    public void onBottomHelpPay() {
        if (checkNum == 0) {
            Toast.makeText(this, "请选中需要操作的卡", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dialog == null) {
            dialog = new NormalDialog(this, R.style.ios_bottom_dialog, R.layout.dialog_repay);
        }
        dialog.show();

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        tvTitle.setText("代付确认");

        TextView tvMoney = dialog.findViewById(R.id.tv_money);
        tvMoney.setText("笔数：" + checkNum + " 金额：¥" + StringUtil.getTwoPointString(money));

        TextView tvTextSure = dialog.findViewById(R.id.tv_text_sure);
        tvTextSure.setText("确认代付吗");

        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView tvSure = dialog.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                StringBuffer sb = new StringBuffer();
                ArrayList<Integer> positions = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChecked()) {
                        sb.append(list.get(i).getPlanId())
                                .append(",");
                        positions.add(i);
                    }
                }
                String planId = sb.substring(0, sb.length() - 1);
                Log.i("---", "planId---" + planId);
                mPresenter.helpPay(planId, positions);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }

        if (datePickerDialog != null) {
            datePickerDialog = null;
        }
    }

    /**
     * 日期选择监听
     *
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
        String day = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
        String date = year + "-" + month + "-" + day;
        if (startDateClick) {
            tvStartDate.setText(date);
            startDateClick = false;
        } else {
            tvEndDate.setText(date);
        }
    }


    /**
     * 选择起始时间
     */
//    @OnClick(R.id.tv_start_date)
    public void selectorStartDate() {
        startDateClick = true;
        if (datePickerDialog == null) {
            setDatePickerDialog();
        }
        datePickerDialog.show(getFragmentManager(), "IntelligentPlanningActivity");
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startDateClick = false;
            }
        });
    }


    /**
     * 终止时间
     */
//    @OnClick(R.id.tv_end_date)
    public void selectorEndDate() {
        if (datePickerDialog == null) {
            setDatePickerDialog();
        }
        datePickerDialog.show(getFragmentManager(), "IntelligentPlanningActivity");

    }


    /**
     * 设置日期选择器
     */
    private void setDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(
                this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        //设置选择器的颜色
        datePickerDialog.setAccentColor("#474E7A");
        //设置标题
        datePickerDialog.setTitle("日期选择");
        //设置选择器的选择范围
//        datePickerDialog.setMaxDate(calendar);
    }


    /**
     * 全选
     */
//    @OnClick(R.id.tv_selector_all)
    public void onSelectorAll() {
        isSelectorAll = !isSelectorAll;
        if (isSelectorAll) {
            for (BatchRepay.ResultBean batchRepay : list) {
                if (!batchRepay.isChecked()) {
                    batchRepay.setChecked(true);
                    money += Float.valueOf(batchRepay.getAmt());
                }
            }
            checkNum = list.size();
            tvSelectorAll.setText("反选");
        } else {
            for (BatchRepay.ResultBean batchRepay : list) {
                batchRepay.setChecked(false);
            }
            money = 0;
            checkNum = 0;
            tvSelectorAll.setText("全选");
        }

        tvAll.setText(getResources().getString(R.string.bottom, checkNum, StringUtil.getTwoPointString(money)));
        adapter.notifyDataSetChanged();

    }


    /**
     * 操作记录
     */
    @OnClick(R.id.iv_operation_record)
    public void toOperationRecord() {
        ActivityIntent.readyGo(this, OperationRecordActivity.class);
    }

    /**
     * 获取代付账号信息
     *
     * @param payAccount
     */
    @Override
    public void getPayAccount(PayAccount payAccount) {
        tvAccount.setText(getResources().getString(R.string.helpPay, payAccount.getPayAccount()));
        tvMoney.setText(getResources().getString(R.string.money, StringUtil.getTwoPointString(payAccount.getBalance())));
    }

    /**
     * 获取还款代付列表
     *
     * @param batchRepay
     */
    @Override
    public void getPlanQuery(BatchRepay batchRepay) {
        List<BatchRepay.ResultBean> result = batchRepay.getResult();
        totalPage = batchRepay.getTotalPage();
        if (result != null) {
            if (isQuery) {
                list.clear();
            }
            list.addAll(result);
        }
        adapter.notifyDataSetChanged();
        isQuery = false;

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
        showCheckedNum();
    }

    /**
     * 代付成功 删除列表数据时，应该从后往前删
     *
     * @param positions
     */
    @Override
    public void helpPaySuccess(ArrayList<Integer> positions) {
        for (int i = positions.size() - 1; i >= 0; i--) {
            int a = positions.get(i);
            list.remove(a);
        }
        adapter.notifyDataSetChanged();
        showCheckedNum();
    }

    //    @OnClick(R.id.tv_refresh)
    public void onTvRefresh() {
        mPresenter.balance();
    }

    /**
     * 上拉监听
     */
    @Override
    public void onLoad() {
        if (totalPage >= pageNum + 1) {
            String startDate = "";
            String textStartDate = tvStartDate.getText().toString().replace("-","");
            if (!textStartDate.equals("起始时间")) {
                startDate = textStartDate;
            }
            String endDate = "";
            String textEndDate = tvEndDate.getText().toString().replace("-","");
            if (!textEndDate.equals("终止时间")) {
                endDate = textEndDate;
            }
            String bankCard = edtBankCard.getText().toString().trim();
            String num = edtNum.getText().toString().trim();

            if (TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
                startDate = endDate;
            }
            mPresenter.planQuery(startDate, endDate, num, bankCard, Config.NOT_OPERATOR, Config.REPAY, "0", ++pageNum + "");
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(this, "没有更多数据啦", Toast.LENGTH_SHORT).show();

        }
    }


    /**
     * 重置
     */
//    @OnClick(R.id.tv_reset)
    public void onTvReset() {
        isQuery = true;
        tvStartDate.setText("起始时间");
        tvEndDate.setText("终止时间");
        edtNum.setText("");
        edtBankCard.setText("");
        hideSoftInput();
        pageNum = 1;
        mPresenter.planQuery("", "", "", "", Config.NOT_OPERATOR, Config.REPAY, "1", "1");
    }

    /**
     * 查询
     */
//    @OnClick(R.id.tv_query)
    public void onTvQuery() {
        hideSoftInput();
        String startDate = "";
        String textStartDate = tvStartDate.getText().toString().replace("-","");
        if (!textStartDate.equals("起始时间")) {
            startDate = textStartDate;
        }
        String endDate = "";
        String textEndDate = tvEndDate.getText().toString().replace("-","");
        if (!textEndDate.equals("终止时间")) {
            endDate = textEndDate;
        }
        String bankCard = edtBankCard.getText().toString().trim();
        String num = edtNum.getText().toString().trim();
        if (TextUtils.isEmpty(startDate) && TextUtils.isEmpty(endDate) && TextUtils.isEmpty(bankCard) && TextUtils.isEmpty(num)) {
            Toast.makeText(this, "请填写查询条件", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
            startDate = endDate;
        }
        isQuery = true;
        pageNum = 1;
        mPresenter.planQuery(startDate, endDate, num, bankCard, Config.NOT_OPERATOR, Config.REPAY, "0", "1");
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 代付或还款后 计算底部的数量或者金额
     */
    public void showCheckedNum() {
        checkNum = 0;
        money = 0f;
        for (BatchRepay.ResultBean resultBean : list) {
            if (resultBean.isChecked()) {
                checkNum += 1;
                money += Float.valueOf(resultBean.getAmt());
            }
        }
        tvAll.setText(getResources().getString(R.string.bottom, checkNum, StringUtil.getTwoPointString(money)));

    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
        recyclerView.onStopLoad();
    }

    @OnClick(R.id.iv_go_top)
    public void goTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_refresh) {
            onTvRefresh();
        } else if (v.getId() == R.id.tv_selector_all) {
            onSelectorAll();
        } else if (v.getId() == R.id.tv_start_date) {
            selectorStartDate();
        } else if (v.getId() == R.id.tv_end_date) {
            selectorEndDate();
        } else if (v.getId() == R.id.tv_query) {
            onTvQuery();
        } else if (v.getId() == R.id.tv_reset) {
            onTvReset();
        }
    }
}
