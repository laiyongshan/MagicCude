package com.rflash.magiccube.ui.cardmanager.cardcharge;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.cardbill.CardBillContract;

/**
 * Created by lenovo on 2018/10/24.
 */

public class CardChargeContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(CardChargerBean response);
    }

    interface  Presenter extends BasePresenter<CardChargeContract.View> {
        //queryCard 查询卡片收款信息
        void queryCardfee(String cardNo,String startTime,String pageNum);
    }
}
