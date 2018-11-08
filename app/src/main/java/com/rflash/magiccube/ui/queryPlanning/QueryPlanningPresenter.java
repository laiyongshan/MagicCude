package com.rflash.magiccube.ui.queryPlanning;

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
 * @author lys
 * @time 2018/11/6 16:37
 * @desc:
 */

public class QueryPlanningPresenter extends BasePresenterImpl<QueryPlanningContract.View> implements QueryPlanningContract.Presenter  {
    //规划查询
    @Override
    public void queryPlan(String startDate,String endDate,String cardSeqno,String customerName,String cardNo,String state,
<<<<<<< HEAD
                          String tranType,String accountType,String syncState,String pageNum,String count) {
=======
                          String tranType,String accountType,String syncState,String pageNum) {
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
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
        treeMap.put("cardNo",cardNo);
        treeMap.put("tranType",tranType);
        treeMap.put("startDate",startDate);
        treeMap.put("endDate",endDate);
        treeMap.put("cardSeqno",cardSeqno);
        treeMap.put("customerName",customerName);
        treeMap.put("state",state);
        treeMap.put("accountType",accountType);
        treeMap.put("syncState",syncState);
<<<<<<< HEAD
        treeMap.put("count",count);
=======
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().queryPlanning(version, requestNo, machineCode, account, signature, pageNum,cardNo,tranType,
<<<<<<< HEAD
                    cardSeqno,customerName,state,accountType,startDate,endDate,syncState,count);
=======
                    cardSeqno,customerName,state,accountType,startDate,endDate,syncState);
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<PlaningBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(PlaningBean data) {
                    mView.getDataSuccess(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD


    //同步规划
    @Override
    public void syncPlan() {
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


        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().syncPlan(version, requestNo, machineCode, account, signature);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<BaseBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess() {
                    mView.syncPlanSuccess();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

=======
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
}
