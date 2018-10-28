package com.rflash.magiccube.ui.operationrecord;


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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.view.DefaultLoadCreator;
import com.rflash.magiccube.view.NormalDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 操作记录
 */

public class OperationRecordActivity extends MVPBaseActivity<OperationRecordContract.View, OperationRecordPresenter> implements OperationRecordContract.View, DatePickerDialog.OnDateSetListener, LoadRecyclerView.OnLoadMoreListener, View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    @BindView(R.id.iv_nodata)
    ImageView ivNoData;


    //当前页数
    private int pageNum = 1;
    //总页数
    private int totalPage;

    //是否是点击查询或者重置
    private boolean isQuery;


    //日期选择器dialog
    private DatePickerDialog datePickerDialog;

    //是否选中起始时间按钮
    private boolean startDateClick = false;

    private NormalDialog dialog;


    //开始时间
//    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    //结束时间
//    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

    //    @BindView(R.id.edt_bankcard)
    EditText edtBankCard;

    //    @BindView(R.id.edt_num)
    EditText edtNum;
    //默认的state
    private StringBuffer state = new StringBuffer().append(Config.DEAL)
            .append(",")
            .append(Config.ACCEPTED_FAILURE)
            .append(",")
            .append(Config.WAIT_CONFIRM);

    private StringBuffer newState = new StringBuffer();

    private OperationRecordAdapter adapter;

    private List<OperationRecord.ResultBean> list = new ArrayList<>();

    private boolean deal;
    private boolean acceptedFailure;
    private boolean accepted;

    private View headView;

    @BindView(R.id.iv_go_top)
    ImageView ivGoTop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_record);
        initView();

        headView = LayoutInflater.from(this).inflate(R.layout.layout_record_head, recyclerView, false);
        tvStartDate = headView.findViewById(R.id.tv_start_date);
        tvEndDate = headView.findViewById(R.id.tv_end_date);
        edtBankCard = headView.findViewById(R.id.edt_bankcard);
        edtNum = headView.findViewById(R.id.edt_num);
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
                OperationRecordActivity.this.finish();
            }
        });

        adapter = new OperationRecordAdapter(this, list, R.layout.layout_operation_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置上拉监听
        recyclerView.setOnLoadMoreListener(this);
        //添加空白界面 添加到根布局
        recyclerView.addEmptyView(ivNoData);
        //设置默认的上拉
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
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
        mPresenter.planQuery("", "", "", "", state.toString(), Config.REPAY, "1", "1");
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
     * 悬浮按钮点击
     */
    @OnClick(R.id.iv_floating)
    public void onFloatingClick() {
        if (dialog == null) {
            dialog = new NormalDialog(this, R.style.ios_bottom_dialog, R.layout.dialog_operation_record);
        }
        dialog.show();
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
                Log.i("---checked", "deal :" + deal + "  accepted:" + accepted + "  acceptedFailure:" + acceptedFailure);

                newState.setLength(0);
                if (deal) {
                    newState.append(Config.DEAL)
                            .append(",");
                    isQuery = true;
                }
                if (accepted) {
                    newState.append(Config.WAIT_CONFIRM)
                            .append(",");
                    isQuery = true;

                }
                if (acceptedFailure) {
                    newState.append(Config.ACCEPTED_FAILURE)
                            .append(",");
                    isQuery = true;

                }
                if (newState.length() != 0) {
                    String state = newState.substring(0, newState.length() - 1);
                    mPresenter.planQuery("", "", "", "", state, Config.REPAY, "1", "1");
                }
            }
        });
        //已操作
        CheckBox cbDeal = dialog.findViewById(R.id.cb_deal);
        cbDeal.setChecked(false);
        deal = false;
        cbDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deal = ((CheckBox) v).isChecked();

            }
        });
        View rlDeal = dialog.findViewById(R.id.rl_deal);
        rlDeal.setTag(cbDeal);
        rlDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cbDeal = (CheckBox) v.getTag();
                boolean isChecked = !cbDeal.isChecked();
                cbDeal.setChecked(isChecked);
                deal = isChecked;
            }
        });

        //正在受理
        CheckBox cbAccepted = dialog.findViewById(R.id.cb_accepted);
        cbAccepted.setChecked(false);
        accepted = false;
        cbAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accepted = ((CheckBox) v).isChecked();

            }
        });
        View rlAccepted = dialog.findViewById(R.id.rl_accepted);
        rlAccepted.setTag(cbAccepted);
        rlAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cbAccepted = (CheckBox) v.getTag();
                boolean isChecked = !cbAccepted.isChecked();
                cbAccepted.setChecked(isChecked);
                accepted = isChecked;
            }
        });


        //受理失败
        CheckBox cbAcceptedFailure = dialog.findViewById(R.id.cb_accepted_failure);
        cbAcceptedFailure.setChecked(false);
        acceptedFailure = false;
        cbAcceptedFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptedFailure = ((CheckBox) v).isChecked();

            }
        });
        View rlAcceptedFailure = dialog.findViewById(R.id.rl_accepted_failure);
        rlAcceptedFailure.setTag(cbAcceptedFailure);
        rlAcceptedFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cbAcceptedFailure = (CheckBox) v.getTag();
                boolean isChecked = !cbAcceptedFailure.isChecked();
                cbAcceptedFailure.setChecked(isChecked);
                acceptedFailure = isChecked;
            }
        });


    }


    @Override
    protected void onPause() {
        super.onPause();

        if (datePickerDialog != null) {
            datePickerDialog = null;
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }

    /**
     * 上拉加载更多
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
            if (newState.length() != 0) {
                String state = newState.substring(0, newState.length() - 1);
                mPresenter.planQuery(startDate, endDate, num, bankCard, state, Config.REPAY, "0", ++pageNum + "");
            } else {
                mPresenter.planQuery(startDate, endDate, num, bankCard, state.toString(), Config.REPAY, "0", ++pageNum + "");
            }
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(this, "没有更多数据啦", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 查询成功
     */
    @Override
    public void querySuccess(OperationRecord data) {
        totalPage = data.getTotalPage();
        List<OperationRecord.ResultBean> result = data.getResult();
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
     * 重置
     */
//    @OnClick(R.id.tv_reset)
    public void onTvReset() {
        isQuery = true;
        tvStartDate.setText("起始时间");
        tvEndDate.setText("终止时间");
        edtNum.setText("");
        edtBankCard.setText("");
        //清空newState
        newState.setLength(0);
        hideSoftInput();
        pageNum = 1;
        mPresenter.planQuery("", "", "", "", state.toString(), Config.REPAY, "1", "1");
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
        // 如果只输入终止时间，按起始时间算
        if (TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
            startDate = endDate;
        }
        isQuery = true;
        pageNum = 1;
        if (newState.length() != 0) {
            String state = newState.substring(0, newState.length() - 1);
            mPresenter.planQuery(startDate, endDate, num, bankCard, state, Config.REPAY, "0",  "1");
        } else {
            mPresenter.planQuery(startDate, endDate, num, bankCard, state.toString(), Config.REPAY, "0",  "1");
        }
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_start_date) {
            selectorStartDate();
        } else if (v.getId() == R.id.tv_end_date) {
            selectorEndDate();
        } else if (v.getId() == R.id.tv_query) {
            onTvQuery();
        } else if (v.getId() == R.id.tv_reset) {
            onTvReset();
        }
    }


    @OnClick(R.id.iv_go_top)
    public void goTop() {
        recyclerView.smoothScrollToPosition(0);
    }

}
