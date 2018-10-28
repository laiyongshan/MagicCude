package com.rflash.magiccube.ui.creditrecharge;

import android.widget.Toast;

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
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditrechargePresenter extends BasePresenterImpl<CreditrechargeContract.View> implements CreditrechargeContract.Presenter{

    @Override
    public void rechargeTimes(String creditType, String num, String payType) {
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
        treeMap.put("creditType", creditType);
        treeMap.put("num", num);
        treeMap.put("payType", payType);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> queryCreditProduct = RetrofitFactory.getApiService().rechargeTimes(version, requestNo, machineCode, account, creditType, num, payType, signature);
            Observable<BaseBean> compose = queryCreditProduct.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<CreditRechare>((BaseActivity) mView) {
                @Override
                protected void onSuccess(CreditRechare data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.showQrcode(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
