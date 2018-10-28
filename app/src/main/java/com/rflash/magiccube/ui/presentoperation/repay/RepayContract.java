package com.rflash.magiccube.ui.presentoperation.repay;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.presentoperation.Operation;

/**
 */

public class RepayContract {
    interface View extends BaseView {
        void querySuccess(Operation operation);
        void repaySuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        //今日操作
        void planQuery(String state, String tranType, String today, String pageNum,String cardNum);
        //确认还款
        void confirmRepay(String planId);

    }
}
