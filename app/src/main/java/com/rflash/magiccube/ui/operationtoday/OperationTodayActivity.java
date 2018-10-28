package com.rflash.magiccube.ui.operationtoday;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.operationtoday.operation.OperationFragment;
import com.rflash.magiccube.ui.operationtoday.unoperation.UnOperationFragment;
import com.rflash.magiccube.ui.presentoperation.OperationPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 今日操作界面
 */
@Deprecated
public class OperationTodayActivity extends MVPBaseActivity<OperationTodayContract.View, OperationTodayPresenter> implements OperationTodayContract.View, ViewPager.OnPageChangeListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.tv_reduction)
    TextView tvReduction;

    private int operation = 0;
    private int unOperation = 0;

    private OperationPageAdapter adapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    private OperationFragment operationFragment;
    private UnOperationFragment unOperationFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_today);
        initView();
        viewpager.addOnPageChangeListener(this);
    }

    private void initView() {
        toolbar.setTitle("今日操作");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperationTodayActivity.this.finish();
            }
        });
        operationFragment = new OperationFragment();
        unOperationFragment = new UnOperationFragment();
        fragmentList.add(unOperationFragment);
        fragmentList.add(operationFragment);
        adapter = new OperationPageAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
    }

    @OnClick(R.id.tv_reduction)
    public void reduction() {
        tvReduction.setVisibility(View.GONE);
        int currentItem = viewpager.getCurrentItem();
        if (currentItem == 0){//未操作
            unOperationFragment.clickItem = false;
            unOperationFragment.mRefresh = true;
            unOperationFragment.pageNum = 1;
            unOperationFragment.cardNum = "";
            unOperationFragment.mPresenter.planQuery(Config.NOT_OPERATOR, "", "1",  "1","");
            unOperation = 0;
        }else {
            operationFragment.clickItem = false;
            operationFragment.refresh = true;
            operationFragment.pageNum = 1;
            operationFragment.cardNum = "";
            operationFragment.mPresenter.planQuery(Config.DEAL, "", "1", "1", "");
            operation = 0;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {//未操作
            if (unOperation == 0) {//还原按钮消失
                tvReduction.setVisibility(View.GONE);
            } else {
                tvReduction.setVisibility(View.VISIBLE);
            }
        } else {//已操作
            if (operation == 0) {//还原按钮消失
                tvReduction.setVisibility(View.GONE);
            } else {
                tvReduction.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 改变 operation unOperation 的值
     */
    public void change(String tag){
        if (tag.equals(Config.NOT_OPERATOR)){//未操作
            unOperation = 1;
            tvReduction.setVisibility(View.VISIBLE);
        }else if (tag.equals(Config.DEAL)){
            operation = 1;
            tvReduction.setVisibility(View.VISIBLE);
        }
    }


    public void refresh(){
        operationFragment.onRefresh();
    }

}
