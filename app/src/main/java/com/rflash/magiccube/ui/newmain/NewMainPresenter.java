package com.rflash.magiccube.ui.newmain;

import android.util.Log;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.main.AppInfo;
import com.rflash.magiccube.ui.main.BillCount;
import com.rflash.magiccube.ui.main.MainContract;
import com.rflash.magiccube.ui.salesmen.SalesmenBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/11/1.
 */

public class NewMainPresenter extends BasePresenterImpl<NewMainContract.View> implements NewMainContract.Presenter{

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


    //字典同步
    @Override
    public void queryDirt() {
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

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> updateApp = RetrofitFactory.getApiService().queryDict(version, requestNo, machineCode, account, signature);
            Observable<BaseBean> compose = updateApp.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<DirtBean>((BaseActivity) mView) {

                @Override
                public void onComplete() {
                    super.onComplete();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }

                @Override
                protected void onSuccess(DirtBean data) {
                    mView.queryDirtSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void querySalesMan(String name, String profitRatio) {
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
        treeMap.put("name",name);
        treeMap.put("profitRatio",profitRatio);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().querySalesMan(version, requestNo, machineCode, account, signature,name,profitRatio);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<SalesmenBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(SalesmenBean data) {
                    super.onSuccess(data);
                    mView.querySalMenSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
