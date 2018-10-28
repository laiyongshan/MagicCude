package com.rflash.magiccube.ui.operationrecord;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class OperationRecordContract {
    interface View extends BaseView {
        void querySuccess(OperationRecord data);
    }

    interface Presenter extends BasePresenter<View> {
        void planQuery(String startDate, String endDate, String cardSeqno, String cardNo, String state, String tranType, String today, String pageNum);
    }
}
