package com.rflash.magiccube.ui.renewal;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.newmain.DirtBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * @author lys
 * @time 2018/11/2 14:40
 * @desc:
 */

public class RenewalPresenter extends BasePresenterImpl<RenewalContract.View> implements RenewalContract.Presenter {


    @Override
    public void overdueRenewal(String cardNo, String serviceEndDate, String serviceType, String serviceAmt, String serviceRate, String paidAmt,
                               String fixedLimit, String currentRepayAmt, String initAmt, String serviceStartDate,
                               String availableAmt, String state) {
        String signature = "";
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("cardNo",cardNo);
        treeMap.put("serviceEndDate",serviceEndDate);
        treeMap.put("serviceType",serviceType);
        treeMap.put("serviceAmt",serviceAmt);
        treeMap.put("serviceRate",serviceRate);
        treeMap.put("paidAmt",paidAmt);
        treeMap.put("fixedLimit",fixedLimit);
        treeMap.put("currentRepayAmt",currentRepayAmt);
        treeMap.put("initAmt",initAmt);
        treeMap.put("serviceStartDate",serviceStartDate);
        treeMap.put("availableAmt",availableAmt);
        treeMap.put("state",state);


        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> updateApp = RetrofitFactory.getApiService().renewalCard(version, requestNo, machineCode, account, signature, cardNo,  serviceEndDate,  serviceType,  serviceAmt,
                    serviceRate,  paidAmt,
                     fixedLimit,  currentRepayAmt,  initAmt,  serviceStartDate,
                     availableAmt,  state);
            Observable<BaseBean> compose = updateApp.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {

                @Override
                public void onComplete() {
                    super.onComplete();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }

                @Override
                protected void onSuccess() {
                    mView.renewalResult();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void noOverdueRewal() {

    }
}
