package com.rflash.magiccube.ui.creditpersonalmessage;

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

public class CreditPersonalMessagePresenter extends BasePresenterImpl<CreditPersonalMessageContract.View> implements CreditPersonalMessageContract.Presenter{

    @Override
    public void deletePersonalMessage(String orderNo) {
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
        treeMap.put("orderNo", orderNo);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> deletePersonalMessage = RetrofitFactory.getApiService().deletePersonalMessage(version, requestNo, machineCode, account, orderNo, signature);
            Observable<BaseBean> compose = deletePersonalMessage.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                protected void onSuccess() {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.retuenUpActivity();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
