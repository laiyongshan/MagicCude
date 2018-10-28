package com.rflash.magiccube.ui.creditqueryitem;

import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditQueryItemPresenter extends BasePresenterImpl<CreditQueryItemContract.View> implements CreditQueryItemContract.Presenter{
    @Override
    public void queryProduct(String name, String identityCard, String cardNo, String mobile, String beginDate, String endDate, String creditType) {
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
        treeMap.put("name", name);
        treeMap.put("identityCard", identityCard);
        treeMap.put("cardNo", cardNo);
        treeMap.put("mobile", mobile);
        treeMap.put("beginDate", beginDate);
        treeMap.put("endDate", endDate);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> queryCreditProduct = RetrofitFactory.getApiService().queryProduct(version, requestNo, machineCode, account, creditType, cardNo, name, identityCard, mobile, beginDate, endDate, signature);
            Observable<BaseBean> compose = queryCreditProduct.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<CreditQueryItem>((BaseActivity) mView) {
                @Override
                protected void onSuccess(CreditQueryItem data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.finishActivity();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
