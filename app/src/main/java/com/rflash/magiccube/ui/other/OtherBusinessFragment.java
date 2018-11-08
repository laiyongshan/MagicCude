package com.rflash.magiccube.ui.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.basemodule.BaseFragment;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.credithandle.CreditHandleActivity;
import com.rflash.magiccube.ui.creditquery.CreditQueryActivity;
import com.rflash.magiccube.ui.loanhandle.LoanHandleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/10/6.
 */

public class OtherBusinessFragment extends BaseFragment {

    @BindView(R.id.other_rv)
    RecyclerView other_rv;

    OtherBusinessAdapter mOtherBusinessAdapter;

    List<OtherBean> mList=new ArrayList<>();

    private View view;

    @Override
    protected int getLayout() {
        return R.layout.fragment_other;
    }

    @Nullable
    @Override
    protected void initView(){

        mList.add(new OtherBean("信用卡办理",R.mipmap.icon_other1));
        mList.add(new OtherBean("信用查询",R.mipmap.icon_other2));
        mList.add(new OtherBean("贷款业务",R.mipmap.icon_other3));

        other_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOtherBusinessAdapter=new OtherBusinessAdapter(mList);

        other_rv.setAdapter(mOtherBusinessAdapter);
    }


}
