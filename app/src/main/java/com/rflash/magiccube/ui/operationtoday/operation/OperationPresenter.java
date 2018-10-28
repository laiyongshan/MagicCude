package com.rflash.magiccube.ui.operationtoday.operation;

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

public class OperationPresenter extends BasePresenterImpl<OperationContract.View> implements OperationContract.Presenter {

    private static final String TAG = "OperationPresenter";

    /**
     * 今日操作
     *
     * @param state    状态 NOT_OPERATOR：未操作    DEAL：已操作
     * @param tranType 操作类型 SALE：消费  REPAY：还款
     * @param today    0或不传，不限定   1限定   当为1时，开始结束日期会被忽略
     * @param pageNum  查询的页数 分页的时候的页数，不传值的话会默认1，就是返回分页第一页内容
     */
    @Override
    public void planQuery(String state, String tranType, String today, String pageNum,String cardNum) {
        String startDate = "";
        String endDate = "";
        String cardSeqno = "";
        String cardNo = cardNum;
        String signature = "";
        String order = "p.operator_time desc";
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
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> planQuery = RetrofitFactory.getApiService().planQuery(version, requestNo, machineCode, account, signature, pointId,startDate,endDate,cardSeqno,cardNo, state, tranType, today, pageNum,order);
            Observable<BaseBean> compose = planQuery.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<Operation>((DefaultView) mView) {

                @Override
                protected void onSuccess(Operation data) {
                    mView.getOperationItem(data);
                    Toast.makeText(mView.getContext(), "请求成功", Toast.LENGTH_SHORT).show();
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "签名出错");

        }

    }
}
