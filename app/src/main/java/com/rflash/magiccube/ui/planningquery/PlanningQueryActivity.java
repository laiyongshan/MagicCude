package com.rflash.magiccube.ui.planningquery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 规划查询
 * 废弃原因:不要规划查询
 */

@Deprecated
public class PlanningQueryActivity extends MVPBaseActivity<PlanningQueryContract.View, PlanningQueryPresenter> implements PlanningQueryContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PlanningQueryAdapter adapter;
    private List<PlanningCard> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_query);

        initView();
    }

    private void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanningQueryActivity.this.finish();
            }
        });

        createDate();
        adapter = new PlanningQueryAdapter(this, list, R.layout.layout_planning_query);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void createDate() {

        PlanningCard planningCard = null;
        for (int i = 0; i < 10; i++) {
            planningCard = new PlanningCard();
            planningCard.setCardNum(i + "");
            planningCard.setBank("中国银行");
            planningCard.setName("rflash");
            planningCard.setBankCard("666666****444" + i);
            planningCard.setStartDate("2017-10-20");
            planningCard.setEndDate("2017-10-2" + i);
            planningCard.setBillDate("14日");
            planningCard.setRepayDate("18日");
            list.add(planningCard);
        }


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
}
