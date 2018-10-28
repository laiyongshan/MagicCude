package com.rflash.magiccube.ui.login;

import android.util.Log;
import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.AssetsUtil;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.main.MainActivity;
import com.rflash.magiccube.ui.newmain.NewMainActivity;
import com.rflash.magiccube.util.AESUtil;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    public static final String TAG = "LoginPresenter";

    /**
     * 保存用户信息
     *
     * @param checked
     * @param name
     * @param pwd
     */
    @Override
    public void saveUserInfo(boolean checked, String name, String pwd) {
        SpUtil.putString(mView.getContext(), Config.ACCOUNT, name);
        SpUtil.putString(mView.getContext(), Config.USER_PWD, pwd);
        SpUtil.putBoolean(mView.getContext(), Config.USER_STORE, checked);

    }

    /**
     * 登录
     *
     * @param version
     * @param machineCode
     * @param account
     * @param password    未加密
     */
    @Override
    public void login(String version, String machineCode, final String account, final String password) {

        String signature = "";
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String encodePwd = AESUtil.encrypt(password, Config.AES);
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("password", encodePwd);

        try {
            signature = SignUtil.signDataWithStr(treeMap, AssetsUtil.readAssetsTxt(mView.getContext(), Config.DEFAULT_PRIVATE_PATH));
            Observable<BaseBean> login = RetrofitFactory.getApiService().login(version, requestNo, machineCode, account, signature, encodePwd);
            Observable<BaseBean> compose = login.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Login>((BaseActivity) mView) {

                @Override
                protected void onSuccess(Login data) {
                    String mc = "POS_"+data.getPosMachineCode() ;
                    if (mc.equals(SpUtil.getString(mView.getContext(), Config.MACHINECODE, ""))) {
                        String encryptPrvKey = AESUtil.decrypt(data.getPrvKey(), Config.AES);
//                    String encryptPubKey = AESUtil.decrypt(data.getPubKey(), Config.AES);
//                    SpUtil.putString(mView.getContext(), Config.USER_PUBKEY, encryptPubKey);
                        SpUtil.putString(mView.getContext(), Config.USER_PRVKEY, encryptPrvKey);
                        SpUtil.putString(mView.getContext(), Config.POINT_ID, data.getPointId());
                        SpUtil.putString(mView.getContext(),Config.TERMCODE,data.getTermCode());
                        Log.i("lys",Config.TERMCODE+""+data.getTermCode());
                        mView.loginSuccess(account, password);
                        ActivityIntent.readyGo(mView.getContext(), NewMainActivity.class);
                        ((LoginActivity) mView).finish();
                    } else {
                        Toast.makeText(mView.getContext(), "账号与设备不匹配，请绑定设备", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //下面的方法是链式调用的
//            login.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle())).subscribe(new DefaultObserver<Login>(mView) {
//
//
//                @Override
//                protected void onSuccess(Login data) {
//
//                }
//            });
        } catch (Exception e) {
            Log.i(TAG, "签名出错");
            e.printStackTrace();
        }


    }


}
