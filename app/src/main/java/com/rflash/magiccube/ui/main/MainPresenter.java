package com.rflash.magiccube.ui.main;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {


    /**
     * 获取账单数量
     */
    @Override
    public void getBillCount() {

        String signature = "";
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
        treeMap.put("pointId", pointId);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> billCount = RetrofitFactory.getApiService().billCount(version, requestNo, machineCode, account, signature, pointId);
            Observable<BaseBean> compose = billCount.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BillCount>((BaseActivity) mView) {
                @Override
                protected void onSuccess(BillCount data) {
                    mView.getBillCount(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 检测app是否需要更新
     */
    @Override
    public void updateApp() {
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
        treeMap.put("type", Config.ANDROID);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> updateApp = RetrofitFactory.getApiService().updateApp(version, requestNo, machineCode, account, signature, Config.ANDROID);
            Observable<BaseBean> compose = updateApp.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<AppInfo>((BaseActivity) mView) {
                @Override
                protected void onSuccess(AppInfo data) {
                    mView.queryUpdateSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
