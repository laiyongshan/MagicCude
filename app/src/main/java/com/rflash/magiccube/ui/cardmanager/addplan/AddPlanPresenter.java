package com.rflash.magiccube.ui.cardmanager.addplan;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInfoContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/27.
 */

public class AddPlanPresenter extends BasePresenterImpl<AddPlanContract.View> implements AddPlanContract.Presenter {

    @Override
    public void addPlan(String tranType, String cardNo, String channel, String merchantCode, String termCode, String amt, String isNeedT0, String date, String state) {
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
        treeMap.put("tranType",tranType);
        treeMap.put("cardNo",cardNo);
        treeMap.put("channel",channel);
        treeMap.put("merchantCode",merchantCode);
        treeMap.put("termCode",termCode);
        treeMap.put("amt",amt);
        treeMap.put("isNeedT0",isNeedT0);
        treeMap.put("date",date);
        treeMap.put("state",state);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> cardbaseinfo = RetrofitFactory.getApiService().addPlan(version, requestNo, machineCode, account, signature,tranType, cardNo,channel,merchantCode,termCode,amt,isNeedT0,date,state);
            Observable<BaseBean> compose = cardbaseinfo.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseInfoBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(BaseInfoBean data) {
                    mView.getResult(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
