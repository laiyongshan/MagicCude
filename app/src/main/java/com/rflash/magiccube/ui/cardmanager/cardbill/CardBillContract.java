package com.rflash.magiccube.ui.cardmanager.cardbill;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.CardManagerContract;

/**
 * Created by lenovo on 2018/10/24.
 */

public class CardBillContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(CardBillBean response);
    }

    interface  Presenter extends BasePresenter<CardBillContract.View> {
        //queryCard 查询卡片账单信息
        void queryBill(String cardNo,String billMonth,String available,String pageNum);
    }
}