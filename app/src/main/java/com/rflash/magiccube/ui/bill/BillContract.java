package com.rflash.magiccube.ui.bill;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class BillContract {
    interface View extends BaseView {
        void getCardBill(Bill bill);
        void updateSuccess(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void queryCardBill(String isConfirm, String state, String pageNum);
        void updateCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate, int position);
    }
}
