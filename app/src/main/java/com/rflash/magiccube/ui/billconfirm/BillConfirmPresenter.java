package com.rflash.magiccube.ui.billconfirm;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.batchrepay.BatchRepayContract;
import com.rflash.magiccube.ui.batchrepay.PayAccount;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/18.
 */

public class BillConfirmPresenter  extends BasePresenterImpl<BillConfirmContract.View> implements BillConfirmContract.Presenter {

    /**
     * 获取账单确认信息列表
     * */
    @Override
    public void getBillConfirmList(String pageNum) {
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
            Observable<BaseBean> bill = RetrofitFactory.getApiService().getBillConfirmList(version, requestNo, machineCode, account, signature, pageNum);
            Observable<BaseBean> compose = bill.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BillConfirmBean>((BaseActivity) mView.getContext()) {

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }

                @Override
                protected void onSuccess(BillConfirmBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate) {
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
        treeMap.put("billId",billId);
        treeMap.put("cardNo",cardNo);
        treeMap.put("billMonth",billMonth);
        treeMap.put("billAmt",billAmt);
        treeMap.put("operate",operate);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> increase = RetrofitFactory.getApiService().updateCardBill(version, requestNo, machineCode, account, signature, billId, cardNo,billMonth,billAmt,operate);
            Observable<BaseBean> compose = increase.compose(((BaseActivity) mView).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mView.getDataFail(e.getMessage());
                }
                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }

                @Override
                protected void onSuccess() {
                    super.onSuccess();
                    mView.updateCardBillSuccess();
                }

                @Override
                protected void onSuccess(BaseBean data) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
