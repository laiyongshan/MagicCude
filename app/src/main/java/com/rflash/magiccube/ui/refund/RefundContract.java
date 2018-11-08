package com.rflash.magiccube.ui.refund;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.billconfirm.BillConfirmBean;
import com.rflash.magiccube.ui.billconfirm.BillConfirmContract;

/**
 * Created by lenovo on 2018/10/18.
 */

public class RefundContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(RefundBean response);
    }

    interface  Presenter extends BasePresenter<View> {

        void getRefundList(String pageNum);

    }
}
