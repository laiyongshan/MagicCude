package com.rflash.magiccube.ui.operationtoday.operation;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.presentoperation.Operation;

/**
 *
 */

public class OperationContract {
    interface View extends BaseView {
        void getOperationItem(Operation operation);
    }

    interface Presenter extends BasePresenter<View> {
        //今日操作
        void planQuery(String state, String tranType, String today, String pageNum, String cardNum);
    }
}
