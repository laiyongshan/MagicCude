package com.rflash.magiccube.ui.shanghu.download;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/20.
 */

public class DownloadPresenter extends BasePresenterImpl<DownloadContrat.View> implements DownloadContrat.Presenter {
    @Override
    public void bindShanghu(String merchant) {
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
        treeMap.put("merchant",merchant);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> balance = RetrofitFactory.getApiService().bindShanghu(version, requestNo, machineCode, account, signature, merchant);
            Observable<BaseBean> compose = balance.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.bindFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess() {
                    mView.bindSuccess();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void queryShanghu(String channelName,String channelState, String merchantName, String merchantCode, String state, String merchantType, String startDate, String endDate, String bind, String pageNum,String pageSize) {
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
        treeMap.put("pageSize",pageSize);
        treeMap.put("channelName",channelName);
        treeMap.put("channelState",channelState);
        treeMap.put("merchantName",merchantName);
        treeMap.put("merchantCode",merchantCode);
        treeMap.put("state",state);
        treeMap.put("merchantType",merchantType);
        treeMap.put("startDate",startDate);
        treeMap.put("endDate",endDate);
        treeMap.put("bind",bind);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> shanghu = RetrofitFactory.getApiService().queryShanghu(version, requestNo, machineCode, account, signature, pageNum,pageSize,
                    channelName,channelState,merchantName,merchantCode,state,merchantType,startDate,endDate,bind);
            Observable<BaseBean> compose = shanghu.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<DownloadBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(DownloadBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
