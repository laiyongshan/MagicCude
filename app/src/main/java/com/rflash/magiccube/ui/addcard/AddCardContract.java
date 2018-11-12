package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * Created by lenovo on 2018/10/28.
 */

public class AddCardContract {
    interface View extends BaseView {
        void showLoading();
        void finishinsertCard();
        void insertCardFail(String msg);
        void insertCardSuccess();
        void verifyResult(VerfyBean data);//验证结果
        void toPayResult(PayBean payBean);//生成支付信息
        void queryProductDetailResult(ProductDetailBean productDetailBean);//查询征信产品信息结果
    }

    interface  Presenter extends BasePresenter<View> {

        //queryCard 添加卡片
        void insertCard(String cardNo, String cardSeqno, String bankCode, String customerName, String customerID, String phone, String billDate,
                        String repayDateType, String repayDate, String salesMan, String fixedLimit, String availableAmt, String currentRepayAmt, String initAmt, String tempLimit,
                        String tempLimitDate, String isHolidayPlan, String isFreePlan, String freePlanRate, String serviceType, String serviceAmt, String serviceRate,
                        String serviceStartDate, String serviceEndDate, String paidAmt, String ebankinfo, String stagesList, String cardMedia
        );

        //四要素验证
        void relCard(String cardNo, String name, String identityCard, String mobile, String cardMedia);

        //征信产品
        //creditType  产品编号
        void productBuy(String creditType, String num, String payType);

        //查询单一征信产品信息
        void productDetail(String creditType);
    }
}
