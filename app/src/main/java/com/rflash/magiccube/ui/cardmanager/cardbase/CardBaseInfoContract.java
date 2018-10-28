package com.rflash.magiccube.ui.cardmanager.cardbase;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.CardManagerContract;

/**
 * Created by lenovo on 2018/10/27.
 */

public class CardBaseInfoContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(BaseInfoBean response);
        void verifyResult();
    }

    interface  Presenter extends BasePresenter<CardBaseInfoContract.View> {

        //queryCard 查询卡片详情信息
        void queryCardDetail(String cardNo);

        //四要素验证
        void relCard(String cardNo,String name,String identityCard,String mobile,String cardMedia);
    }
}
