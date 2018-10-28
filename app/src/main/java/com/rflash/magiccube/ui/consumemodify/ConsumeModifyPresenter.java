package com.rflash.magiccube.ui.consumemodify;

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

public class ConsumeModifyPresenter extends BasePresenterImpl<ConsumeModifyContract.View> implements ConsumeModifyContract.Presenter {

    @Override
    public void queryChannel() {
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
            Observable<BaseBean> queryChannel = RetrofitFactory.getApiService().queryChannel(version, requestNo, machineCode, account, signature);
            Observable<BaseBean> compose = queryChannel.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Channel>((BaseActivity) mView) {
                @Override
                protected void onSuccess(Channel channel) {
                    mView.getChannel(channel);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void queryMerchant(String channel, String merchantName, String pageNum) {
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
        treeMap.put("channel", channel);
        treeMap.put("channelState", Config.CHANNELSTATE_VALID);
        treeMap.put("state", Config.CHANNELSTATE_VALID);
        treeMap.put("bind", Config.BIND_Y);
        treeMap.put("merchantName", merchantName);
        treeMap.put("pageNum", pageNum);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> queryMerchant = RetrofitFactory.getApiService().queryMerchant(version, requestNo, machineCode, account, signature, channel, Config.CHANNELSTATE_VALID, Config.CHANNELSTATE_VALID, Config.BIND_Y, merchantName,pageNum);
            Observable<BaseBean> compose = queryMerchant.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Merchant>((BaseActivity) mView, false) {
                @Override
                protected void onSuccess(Merchant merchant) {
                    mView.getMerchant(merchant, merchantName);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改规划
    @Override
    public void modify(String planId, String amt, String merchantCode, String channel) {
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
        treeMap.put("channel", channel);
        treeMap.put("planId", planId);
        treeMap.put("amt", amt);
        treeMap.put("merchantCode", merchantCode);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> consumeModify = RetrofitFactory.getApiService().consumeModify(version, requestNo, machineCode, account, signature, planId, amt, merchantCode, channel);
            Observable<BaseBean> compose = consumeModify.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                protected void onSuccess() {
                    mView.modifySuccess();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
