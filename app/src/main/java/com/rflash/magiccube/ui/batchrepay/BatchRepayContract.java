package com.rflash.magiccube.ui.batchrepay;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class BatchRepayContract {
    interface View extends BaseView {
        void getPayAccount(PayAccount payAccount);
        void getPlanQuery(BatchRepay batchRepay);
        void repaySuccess(int position);
        void helpPaySuccess(ArrayList<Integer> positions);
    }

    interface  Presenter extends BasePresenter<View> {
        void balance();
        void planQuery(String startDate, String endDate, String cardSeqno, String cardNo, String state, String tranType, String today, String pageNum);
        //确认还款
        void confirm(String planId, int position);
        void helpPay(String planId, ArrayList<Integer> positions);
    }
}
