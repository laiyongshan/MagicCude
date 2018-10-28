package com.rflash.magiccube.ui.operationrecord;

import android.util.Log;
import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.batchrepay.BatchRepay;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 */

public class OperationRecordPresenter extends BasePresenterImpl<OperationRecordContract.View> implements OperationRecordContract.Presenter {

    public static final String TAG = "RecordPresenter";

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
            Log.i(TAG, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> planQuery = RetrofitFactory.getApiService().planQuery(version, requestNo, machineCode, account, signature, pointId, startDate, endDate, cardSeqno, cardNo, state, tranType, today, pageNum,order);
            Observable<BaseBean> compose = planQuery.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<OperationRecord>((BaseActivity) mView) {

                @Override
                protected void onSuccess(OperationRecord data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.querySuccess(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "签名出错");

        }

    }
}
