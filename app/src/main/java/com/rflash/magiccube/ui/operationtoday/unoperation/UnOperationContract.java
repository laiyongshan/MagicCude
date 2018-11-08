package com.rflash.magiccube.ui.operationtoday.unoperation;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.OperationTime;

/**
 */

public class UnOperationContract {
    interface View extends BaseView {
        void getOperationItem(Operation operation);
        void repaySuccess(int position);
        void lastOperator(OperationTime operationTime, OperationItem operationItem);
    }

    interface  Presenter extends BasePresenter<View> {
        //今日操作
        void planQuery(String state, String tranType, String today, String pageNum, String cardNum);
        //确认还款
        void confirm(String planId, int position);
        //	校验卡片最后交易时间
        void lastOperator(OperationItem operationItem);
    }
}
