package com.rflash.magiccube.ui.batchrepay;

import android.util.Log;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 */

public class BatchRepayPresenter extends BasePresenterImpl<BatchRepayContract.View> implements BatchRepayContract.Presenter {

    private static final String TAG = "BatchRepayPresenter";

    /**
     * 查询代付账户可用金额
     */
    @Override
    public void balance() {
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
        treeMap.put("pointId", pointId);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> balance = RetrofitFactory.getApiService().balance(version, requestNo, machineCode, account, signature, pointId);
            Observable<BaseBean> compose = balance.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<PayAccount>((BaseActivity) mView) {
                @Override
                protected void onSuccess(PayAccount data) {
                    mView.getPayAccount(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询还款列表
     *
     * @param state
     * @param tranType
     * @param today
     * @param pageNum
     */
    @Override
    public void planQuery(String startDate, String endDate, String cardSeqno, String cardNo, String state, String tranType, String today, String pageNum) {
        String signature = "";
        String order = "";
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
        treeMap.put("pointId", pointId);
        treeMap.put("state", state);
        treeMap.put("tranType", tranType);
        treeMap.put("today", today);
        treeMap.put("pageNum", pageNum);
        treeMap.put("startDate", startDate);
        treeMap.put("endDate", endDate);
        treeMap.put("cardSeqno", cardSeqno);
        treeMap.put("cardNo", cardNo);
        treeMap.put("order", order);

        try {
//            Log.i(TAG, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> planQuery = RetrofitFactory.getApiService().planQuery(version, requestNo, machineCode, account, signature, pointId, startDate, endDate, cardSeqno, cardNo, state, tranType, today, pageNum, order);
            Observable<BaseBean> compose = planQuery.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BatchRepay>((BaseActivity) mView) {

                @Override
                protected void onSuccess(BatchRepay data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.getPlanQuery(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "签名出错");

        }

    }

    @Override
    public void confirm(String planId, final int position) {

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
        treeMap.put("planId", planId);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().confirm(version, requestNo, machineCode, account, signature, planId);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                protected void onSuccess() {
                    Toast.makeText(mView.getContext(), "确认还款成功", Toast.LENGTH_SHORT).show();
                    mView.repaySuccess(position);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起代付
     *
     * @param planId
     */
    @Override
    public void helpPay(String planId, final ArrayList<Integer> positions) {

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
        treeMap.put("planId", planId);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().helpPay(version, requestNo, machineCode, account, signature, planId);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
                @Override
                protected void onSuccess() {
                    Toast.makeText(mView.getContext(), "代付成功", Toast.LENGTH_SHORT).show();
                    mView.helpPaySuccess(positions);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
