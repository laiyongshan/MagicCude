package com.rflash.magiccube.ui.loanhandle;

import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.credithandle.CardLink;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 *  贷款业务办理界面Presenter
 *  Created by Guobaihui on 2018/03/19.
 */

public class LoanHandlePresenter extends BasePresenterImpl<LoanHandleContract.View> implements LoanHandleContract.Presenter{
    @Override
    public void queryLink(String type) {
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
        treeMap.put("type", type);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> queryCardBill = RetrofitFactory.getApiService().queryLink(version, requestNo, machineCode, account, type, signature);
            Observable<BaseBean> compose = queryCardBill.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<CardLink>((BaseActivity) mView) {
                @Override
                protected void onSuccess(CardLink data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.getHandleLink(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
