package com.rflash.magiccube.ui.cardmanager.carddetail;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.cardmanager.CardManagerContract;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/24.
 */

public class CardDetailPresenter  extends BasePresenterImpl<CardDetailContract.View> implements CardDetailContract.Presenter {
    @Override
    public void updateCardState(String cardNo, String state) {
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
        treeMap.put("cardNo",cardNo);
        treeMap.put("state",state);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> updataCardState = RetrofitFactory.getApiService().updateCardState(version, requestNo, machineCode, account, signature, cardNo,state);
            Observable<BaseBean> compose = updataCardState.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess() {
                    mView.updateCardState();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
