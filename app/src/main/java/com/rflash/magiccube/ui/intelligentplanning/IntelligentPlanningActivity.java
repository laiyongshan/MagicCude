package com.rflash.magiccube.ui.intelligentplanning;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 智能规划界面
 * 废弃原因：不要智能规划界面
 */

@Deprecated
public class IntelligentPlanningActivity extends MVPBaseActivity<IntelligentPlanningContract.View, IntelligentPlanningPresenter> implements IntelligentPlanningContract.View, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

    //日期选择器dialog
    private DatePickerDialog datePickerDialog;

    //是否选中起始时间按钮
    private boolean startDateClick = false;

    private IntelligentPlanningAdapter adapter;
    private List<OperationToday> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent_planning);

        initView();
    }

    private void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntelligentPlanningActivity.this.finish();
            }
        });

        createDate();
        adapter = new IntelligentPlanningAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void createDate() {

        OperationToday operationToday = null;
        for (int i = 0; i < 20; i++) {
            operationToday = new OperationToday();
            operationToday.setBank("中国农业银行");
            operationToday.setBankCard("666666****1111");
            operationToday.setDate("2017-11-11 11:11");
            operationToday.setName("rflash");
            operationToday.setMoney("111");
            operationToday.setCardNum(i + "");

            if (i % 4 == 0) {
                operationToday.setOperationType("1");
            } else if (i % 4 == 1) {
                operationToday.setOperationType("2");
                operationToday.setCompany("广州润莲网络服务有限公司");
                operationToday.setConsumType("D" + i);
            } else if (i % 4 == 2) {
                operationToday.setOperationType("3");
                operationToday.setOperationDate("2017-11-11 11:" + i);
            } else if (i % 4 == 3) {
                operationToday.setOperationType("4");
                operationToday.setCompany("广州润莲网络服务有限公司");
                operationToday.setConsumType("D" + i);
                operationToday.setOperationDate("2017-11-11 11:" + i);

            }
            list.add(operationToday);
        }
    }


    /**
     * 选择起始时间
     */
    @OnClick(R.id.tv_start_date)
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
    @OnClick(R.id.tv_end_date)
    public void selectorEndDate() {
        if (datePickerDialog == null) {
            setDatePickerDialog();
        }
        datePickerDialog.show(getFragmentManager(), "IntelligentPlanningActivity");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public boolean isDismiss() {
        return false;
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
        datePickerDialog.setMaxDate(calendar);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (datePickerDialog != null) {
            datePickerDialog = null;
        }
    }
}
