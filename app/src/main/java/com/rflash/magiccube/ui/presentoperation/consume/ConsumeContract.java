package com.rflash.magiccube.ui.presentoperation.consume;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.OperationTime;

/**
 */

public class ConsumeContract {
    interface View extends BaseView {
        void querySuccess(Operation operation);
        void lastOperator(OperationTime operationTime, OperationItem operationItem);
    }

    interface Presenter extends BasePresenter<View> {
        //今日操作
        void planQuery(String state, String tranType, String today, String pageNum, String cardNum);
        //	校验卡片最后交易时间
        void lastOperator(OperationItem operationItem);
    }
}
