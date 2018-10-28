package com.rflash.magiccube.ui.merchants;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.rflash.basemodule.BaseFragment;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;

import butterknife.BindViews;

/**
 * Created by lenovo on 2018/10/7.
 * 卡片统计概况
 */

@SuppressLint("ValidFragment")
public class CardsSituationFragment extends BaseFragment {

    @BindViews({R.id.card_count_tv,R.id.valid_card_tv,R.id.freeze_card_tv,R.id.serviceDue_card_tv,R.id.downinfo_tv})
    TextView[] tvs;

    HomeCountBean homeCountBean;
    @SuppressLint("ValidFragment")
    public CardsSituationFragment(HomeCountBean homeCountBean){
        this.homeCountBean=homeCountBean;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_card_situation;
    }

    @Override
    protected void initView() {
        tvs[0].setText(homeCountBean.getResult().getCardInfo().getTotal()+"");
        tvs[1].setText(homeCountBean.getResult().getCardInfo().getValid()+"");
        tvs[2].setText(homeCountBean.getResult().getCardInfo().getFreeze()+"");
        tvs[3].setText(homeCountBean.getResult().getCardInfo().getServiceDue()+"");
        tvs[4].setText(homeCountBean.getResult().getDowninfo()+"");

    }

}
