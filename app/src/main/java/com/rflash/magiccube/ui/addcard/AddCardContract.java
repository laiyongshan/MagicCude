package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInfoContract;

/**
 * Created by lenovo on 2018/10/28.
 */

public class AddCardContract {
    interface View extends BaseView {
        void showLoading();
        void finishinsertCard();
        void insertCardFail(String msg);
        void insertCardSuccess(BaseBean response);
        void verifyResult();
    }

    interface  Presenter extends BasePresenter<AddCardContract.View> {

        //queryCard 添加卡片
        void insertCard(String cardNo,String cardSeqno,String bankCode,String customerName,String customerID,String phone,String billDate,
                        String repayDateType,String repayDate,String salesMan,String fixedLimit,String availableAmt,String initAmt,String tempLimit,
                        String tempLimitDate,String isHolidayPlan,String isFreePlan,String freePlanRate,String serviceType,String serviceAmt,String serviceRate,
                        String serviceStartDate,String serviceEndDate,String paidAmt,String ebankinfo,String stagesList,String cardMedia
                        );

        //四要素验证
        void relCard(String cardNo,String name,String identityCard,String mobile,String cardMedia);
    }
}
