package com.rflash.magiccube.ui.refund;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.billconfirm.BillConfirmBean;
import com.rflash.magiccube.ui.billconfirm.BillConfirmContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/18.
 */

public class RefundPresenter extends BasePresenterImpl<RefundContract.View> implements RefundContract.Presenter {

    /**
     * 获取还款账单数据
     * */
    @Override
    public void getRefundList(String pageNum) {
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
        treeMap.put("pageNum",pageNum);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> balance = RetrofitFactory.getApiService().getRefundList(version, requestNo, machineCode, account, signature, pageNum);
            Observable<BaseBean> compose = balance.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<RefundBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(RefundBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
