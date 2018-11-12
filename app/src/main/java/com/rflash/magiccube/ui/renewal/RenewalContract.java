package com.rflash.magiccube.ui.renewal;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/2 14:38
 * @desc:
 */

public class RenewalContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void renewalResult();
    }

    interface  Presenter extends BasePresenter<View> {

        //卡片续期
        //到期状态
        void overdueRenewal(String cardNo,String serviceEndDate,String serviceType,String serviceAmt,String serviceRate,
                            String paidAmt,String fixedLimit,String currentRepayAmt,String initAmt,String serviceStartDate,
                            String availableAmt,String state);

        //未过期状态
        void noOverdueRewal();


    }
}
