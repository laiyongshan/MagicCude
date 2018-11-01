package com.rflash.magiccube.http;


import android.net.ParseException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.utils.AssetsUtil;
import com.rflash.basemodule.utils.BeanUtil;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.util.Base64;
import com.rflash.magiccube.util.SignUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by yangfan on 2017/10/31.
 */
public abstract class DefaultObserver<T extends BaseBean> implements Observer<BaseBean> {

    private DefaultView baseView;
    public static final String TAG = "DefaultObserver";

    private boolean showProgress = true;


    public DefaultObserver(DefaultView baseView) {
        this.baseView = baseView;
        baseView.showProgress();
        Log.i("---", "showProgress");

    }


    public DefaultObserver(DefaultView baseView, boolean showProgress) {
        this.baseView = baseView;
        this.showProgress = showProgress;
        if (showProgress) {
            baseView.showProgress();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (baseView.isDismiss() && showProgress) {
            // 取消订阅
            d.dispose();
        }
    }

    @Override
    public void onNext(BaseBean baseBean) {
        baseView.dismissProgress();
        Log.i(TAG, baseBean.isSuccess()+"");
        //处理结果
        if (baseBean.isSuccess()) {
            //服务器code 正确
            TreeMap<String, String> map = null;
            try {
                //bean转成map
                map = BeanUtil.objectToMap(baseBean);
                //验签
                boolean signSuccess = false;
                signSuccess = SignUtil.verferSignDataWithStr(map, AssetsUtil.readAssetsTxt(baseView.getContext(), Config.DEFAULT_PUBLIC_PATH));

                if (signSuccess) {
                    if (baseBean.getData() != null) {
                        String data = new String(Base64.decode(baseBean.getData()), "utf-8");
                        Log.i(TAG, data.toString());
                        //(Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]
                        //  获取当前类的泛型
                        T t = (T) new Gson().fromJson(data, (Class) ((ParameterizedType) this.getClass().getGenericSuperclass())
                                .getActualTypeArguments()[0]);
                        onSuccess(t);
                    } else {
                        onSuccess();
                    }
                } else {
                    onFail("验签失败，非法登录不通过");
                    Log.i(TAG, "验签失败，非法登录不通过");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(baseView.getContext(), "obj转map失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            //返回错误code
            onFail(baseBean.getRespDesc());
            Log.i(TAG, baseBean.getRespDesc()+"");
        }
    }

    protected void onSuccess(T data) {
    }

    protected void onSuccess() {
    }

    /**
     * @param message 服务器返回的数据
     */
    private void onFail(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(baseView.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onError(Throwable e) {
        Log.e("Retrofit", e.getMessage()+"");
        baseView.dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
        Log.i("---", "onComplete");
    }


    /**
     * 异常
     *
     * @param reason
     */
    private void onException(ExceptionReason reason) {
        switch (reason) {
            case BAD_NETWORK:
                Toast.makeText(baseView.getContext(), "服务器异常", Toast.LENGTH_SHORT).show();
                break;
            case PARSE_ERROR:
                Toast.makeText(baseView.getContext(), "解析服务器响应数据失败", Toast.LENGTH_SHORT).show();
                break;
            case CONNECT_ERROR:
                Toast.makeText(baseView.getContext(), "网络连接失败,请检查网络", Toast.LENGTH_SHORT).show();
                break;
            case CONNECT_TIMEOUT:
                Toast.makeText(baseView.getContext(), "连接超时,请稍后再试", Toast.LENGTH_SHORT).show();
                break;
            case UNKNOWN_ERROR:
                Toast.makeText(baseView.getContext(), "未知错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
