package com.rflash.magiccube.ui.presentoperation.repay;

import android.util.Log;
import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.DefaultView;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.presentoperation.Operation;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 */

public class RepayPresenter extends BasePresenterImpl<RepayContract.View> implements RepayContract.Presenter{
    private static final String TAG = "RepayPresenter";

    /**
     * 查询
     * @param state
     * @param tranType
     * @param today
     * @param pageNum
     * @param cardNo
     */
    @Override
    public void planQuery(String state, String tranType, String today, String pageNum, String cardNo) {
        String startDate = "";
        String endDate = "";
        String cardSeqno = "";
        String signature = "";
        String order = Config.ORDER_SQL;
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
            Observable<BaseBean> planQuery = RetrofitFactory.getApiService().planQuery(version, requestNo, machineCode, account, signature, pointId,startDate,endDate,cardSeqno,cardNo, state, tranType, today, pageNum,order);
            Observable<BaseBean> compose = planQuery.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Operation>((DefaultView) mView) {

                @Override
                protected void onSuccess(Operation data) {
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    mView.querySuccess(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "签名出错");

        }
    }

    /**
     * 确认还款
     * @param planId
     */
    @Override
    public void confirmRepay(String planId) {
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
            compose.subscribe(new DefaultObserver<BaseBean>((DefaultView) mView) {
                @Override
                protected void onSuccess() {
                    mView.repaySuccess();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
