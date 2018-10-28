package com.rflash.magiccube.ui.billconfirm;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.batchrepay.BatchRepay;
import com.rflash.magiccube.ui.batchrepay.BatchRepayContract;
import com.rflash.magiccube.ui.batchrepay.PayAccount;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/10/18.
 */

public class BillConfirmContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(BillConfirmBean response);

    }

    interface  Presenter extends BasePresenter<BillConfirmContract.View> {

        void getBillConfirmList(String pageNum);

    }

}
