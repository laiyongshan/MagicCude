package com.rflash.magiccube.ui.bill;

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
 */

public class BillPresenter extends BasePresenterImpl<BillContract.View> implements BillContract.Presenter {


    @Override
    public void queryCardBill(String isConfirm, String state, String pageNum) {
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
        treeMap.put("isConfirm", isConfirm);
        treeMap.put("state", state);
        treeMap.put("pageNum", pageNum);
        treeMap.put("available", Config.AVAILABLE);
        treeMap.put("showIgnore", Config.SHOWIGNORE);
        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> queryCardBill = RetrofitFactory.getApiService().queryCardBill(version, requestNo, machineCode, account, signature, isConfirm, state, pageNum, Config.AVAILABLE, Config.SHOWIGNORE);
            Observable<BaseBean> compose = queryCardBill.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Bill>((BaseActivity) mView) {
                @Override
                protected void onSuccess(Bill data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.getCardBill(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改账单金额
     *
     * @param billId
     * @param cardNo
     * @param billMonth
     * @param billAmt
     * @param operate
     */
    @Override
    public void updateCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate, final int position) {
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
        treeMap.put("billId", billId);
        treeMap.put("cardNo", cardNo);
        treeMap.put("billMonth", billMonth);
        treeMap.put("billAmt", billAmt);
        treeMap.put("operate", operate);

        try {

            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> updateCardBill = RetrofitFactory.getApiService().updateCardBill(version, requestNo, machineCode, account, signature, billId, cardNo, billMonth, billAmt, operate);
            Observable<BaseBean> compose = updateCardBill.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView) {
                @Override
                protected void onSuccess() {
                    Toast.makeText(mView.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    mView.updateSuccess(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
