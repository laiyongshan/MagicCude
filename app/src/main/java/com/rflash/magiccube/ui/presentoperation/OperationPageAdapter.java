package com.rflash.magiccube.ui.presentoperation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangfan on 2017/11/15.
 * fragment 适配器
 */

public class OperationPageAdapter extends FragmentPagerAdapter {


    private String[] titles = {"消费","还款"};

    private List<Fragment> fragmentList;
    public OperationPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }




    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
