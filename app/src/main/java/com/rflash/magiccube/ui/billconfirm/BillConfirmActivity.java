package com.rflash.magiccube.ui.billconfirm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.refund.HadRefundFragment;
import com.rflash.magiccube.ui.refund.NoRefundFragment;
import com.rflash.magiccube.ui.refund.OverTimeFragment;
import com.rflash.magiccube.ui.refund.RefundActivity;
import com.rflash.magiccube.util.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2018/10/10.
 */

public class BillConfirmActivity extends MVPBaseActivity<BillConfirmContract.View,BillConfirmPresenter> {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;
    @BindView(R.id.trand_type_stl)
    SlidingTabLayout trand_type_stl;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private final String[] mTitles = {"待确认","已确认","已忽略"};

    BillConfirmAdapter billConfirmAdapter;
    List<BillConfirmBean> billConfirmBeanList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_confirm);
        initView();
    }

    private void initView(){
//        bill_confirm_rv.setLayoutManager(new LinearLayoutManager(this));
//        billConfirmAdapter=new BillConfirmAdapter(0,billConfirmBeanList);
//        bill_confirm_rv.setAdapter(billConfirmAdapter);
        mFragments.add(RemidFragment.getInstance());//未確認
        mFragments.add(DealFragment.getInstance());//已確認
        mFragments.add(IgnoreFragment.getInstance());//忽略
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        trand_type_stl.setViewPager(viewpager, mTitles);
    }

    @OnClick({R.id.title_back_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
