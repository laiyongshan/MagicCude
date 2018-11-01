package com.rflash.magiccube.ui.shanghu;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.refund.RefundBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/20.
 */

public class ShanghuPresenter extends BasePresenterImpl<ShanghuContract.View> implements ShanghuContract.Presenter {
    /**
     * 查询商户
     * **/
    @Override
    public void queryShanghu(String channelName, String merchantName,String state, String merchantCode, String merchantType, String startDate, String endDate,String bind,String pageNum) {
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
        treeMap.put("channelName",channelName);
        treeMap.put("merchantName",merchantName);
        treeMap.put("merchantCode",merchantCode);
        treeMap.put("state",state);
        treeMap.put("merchantType",merchantType);
        treeMap.put("startDate",startDate);
        treeMap.put("endDate",endDate);
        treeMap.put("bind",bind);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> shanghu = RetrofitFactory.getApiService().queryShanghu(version, requestNo, machineCode, account, signature, pageNum, channelName,merchantName,merchantCode,state,merchantType,startDate,endDate,bind);
            Observable<BaseBean> compose = shanghu.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<ShanghuBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.getDataFail(e.getMessage()+"");
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(ShanghuBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //商户删除（解绑）
    @Override
    public void unbindShanghu(String merchant) {
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
            Observable<BaseBean> balance = RetrofitFactory.getApiService().unbindShanghu(version, requestNo, machineCode, account, signature, merchant);
            Observable<BaseBean> compose = balance.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.unbindError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.unbindFinish();
                }


                @Override
                protected void onSuccess(BaseBean data) {
                    mView.unbindSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
