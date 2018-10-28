package com.rflash.magiccube.ui.cardmanager.increase;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargeContract;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargerBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/26.
 */

public class CardIncreasePresenter extends BasePresenterImpl<CardIncreaseContract.View> implements CardIncreaseContract.Presenter {
    @Override
    public void queryLimitChange(String cardNo, String changeType, String pageNum) {
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
        treeMap.put("pageNum",pageNum);
        treeMap.put("cardNo",cardNo);
        treeMap.put("changeType",changeType);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> increase = RetrofitFactory.getApiService().queryLimitChange(version, requestNo, machineCode, account, signature, pageNum, cardNo,changeType);
            Observable<BaseBean> compose = increase.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<IncreaseBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.getDataFail(e.getMessage());
                }
                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }
                @Override
                protected void onSuccess(IncreaseBean data) {
                    mView.getDataSuccess(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertLimitChange(String cardNo, String time, String changeType, String amt, String createUser) {
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
        treeMap.put("time",time);
        treeMap.put("cardNo",cardNo);
        treeMap.put("changeType",changeType);
        treeMap.put("amt",amt);
        treeMap.put("createUser",createUser);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> insertLimit = RetrofitFactory.getApiService().insertLimitChange(version, requestNo, machineCode, account, signature, cardNo,time,amt,createUser,changeType);
            Observable<BaseBean> compose = insertLimit.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.getDataFail(e.getMessage());
                }
                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }
                @Override
                protected void onSuccess(BaseBean data) {
                    mView.getDataSuccess(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
