package com.rflash.magiccube.ui.cardmanager.cardbase;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.addcard.PayBean;
import com.rflash.magiccube.ui.addcard.ProductDetailBean;
import com.rflash.magiccube.ui.addcard.VerfyBean;

/**
 * Created by lenovo on 2018/10/27.
 */

public class CardBaseInfoContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(BaseInfoBean response);//获取信息成功
        void verifyResult(VerfyBean data);//验证结果
        void updataSuccess();//修改更新成功
        void toPayResult(PayBean payBean);//生成支付信息
        void queryProductDetailResult(ProductDetailBean productDetailBean);//查询征信产品信息结果
    }

    interface  Presenter extends BasePresenter<View> {

        //queryCard 查询卡片详情信息
        void queryCardDetail(String cardNo);

        //四要素验证
        void relCard(String cardNo, String name, String identityCard, String mobile, String cardMedia);

        //修改卡片信息
        void updateCard(String cardNo, String customerName, String customerID, String phone, String billDate,
                        String repayDateType, String repayDate, String salesMan, String tempLimit,
                        String tempLimitDate, String isHolidayPlan, String isFreePlan, String freePlanRate,String serviceRate, String ebankinfo, String stagesList, String cardMedia);

        //征信产品
        //creditType  产品编号
        void productBuy(String creditType, String num, String payType);

        //查询单一征信产品信息
        void productDetail(String creditType);
    }
}
