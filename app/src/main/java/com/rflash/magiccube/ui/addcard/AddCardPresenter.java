package com.rflash.magiccube.ui.addcard;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInfoContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/28.
 */

public class AddCardPresenter extends BasePresenterImpl<AddCardContract.View> implements AddCardContract.Presenter{

    @Override
    public void insertCard(String cardNo, String cardSeqno, String bankCode, String customerName,
                           String customerID, String phone, String billDate, String repayDateType,
                           String repayDate, String salesMan, String fixedLimit, String availableAmt,
                           String initAmt, String tempLimit, String tempLimitDate, String isHolidayPlan,
                           String isFreePlan, String freePlanRate, String serviceType, String serviceAmt,
                           String serviceRate, String serviceStartDate, String serviceEndDate,
                           String paidAmt, String ebankinfo, String stagesList, String cardMedia) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        String pointId = SpUtil.getString(mView.getContext(), Config.POINT_ID, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("cardNo",cardNo);
        treeMap.put("cardSeqno",cardSeqno);
        treeMap.put("bankCode",bankCode);
        treeMap.put("customerName",customerName);
        treeMap.put("customerID",customerID);
        treeMap.put("phone",phone);
        treeMap.put("billDate",billDate);
        treeMap.put("repayDateType",repayDateType);
        treeMap.put("repayDate",repayDate);
        treeMap.put("salesMan",salesMan);
        treeMap.put("fixedLimit",fixedLimit);
        treeMap.put("availableAmt",availableAmt);
        treeMap.put("initAmt",initAmt);
        treeMap.put("tempLimit",tempLimit);
        treeMap.put("tempLimitDate",tempLimitDate);
        treeMap.put("isHolidayPlan",isHolidayPlan);
        treeMap.put("isFreePlan",isFreePlan);
        treeMap.put("freePlanRate",freePlanRate);
        treeMap.put("serviceType",serviceType);
        treeMap.put("serviceAmt",serviceAmt);
        treeMap.put("serviceRate",serviceRate);
        treeMap.put("serviceStartDate",serviceStartDate);
        treeMap.put("serviceEndDate",serviceEndDate);
        treeMap.put("paidAmt",paidAmt);
        treeMap.put("ebankinfo",ebankinfo);
        treeMap.put("stagesList",stagesList);
        treeMap.put("cardMedia",cardMedia);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> addCard = RetrofitFactory.getApiService().insertCard(version, requestNo, machineCode, account, signature,
                     cardNo,  cardSeqno,  bankCode,  customerName,
                     customerID,  phone,  billDate,  repayDateType,
                     repayDate,  salesMan,  fixedLimit,  availableAmt,
                     initAmt,  tempLimit,  tempLimitDate,  isHolidayPlan,
                     isFreePlan,  freePlanRate,  serviceType,  serviceAmt,
                     serviceRate,  serviceStartDate,  serviceEndDate,
                     paidAmt,  ebankinfo,  stagesList,  cardMedia);
            Observable<BaseBean> compose = addCard.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.insertCardFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishinsertCard();
                }


                @Override
                protected void onSuccess(BaseBean data) {
                    mView.insertCardSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void relCard(String cardNo, String name, String identityCard, String mobile, String cardMedia) {

    }
}