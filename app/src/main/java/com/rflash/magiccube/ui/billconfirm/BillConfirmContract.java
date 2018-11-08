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
        void updateCardBillSuccess();

    }

    interface  Presenter extends BasePresenter<View> {

        //获取账单确认列表
        void getBillConfirmList(String pageNum);

        //修改账单金额,确认账单
        void updateCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate);

    }

}
