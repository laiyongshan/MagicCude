package com.rflash.magiccube.ui.finance;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.finance.financedetail.FinanceDetailBean;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/20.
 */

public class FinanceManagerPresenter extends BasePresenterImpl<FinanceManagerContract.View> implements FinanceManagerContract.Presenter   {
    @Override
    public void queryReport(String cardSeqno, String cardNo, String customerNmae, String salesMan, String startDate, String endDate,String pageNum) {
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
        treeMap.put("cardSeqno",cardSeqno);
        treeMap.put("cardNo",cardNo);
        treeMap.put("customerNmae",customerNmae);
        treeMap.put("salesMan",salesMan);
        treeMap.put("startDate",startDate);
        treeMap.put("endDate",endDate);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().queryReport(version, requestNo, machineCode, account, signature, pageNum,cardSeqno,cardNo,customerNmae,salesMan,startDate,endDate);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<FinanceBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(FinanceBean data) {
                    mView.getDataSuccess(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryPlan(String cardNo, String tranType, String channelId, String pageNum) {
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
        treeMap.put("channelId",channelId);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> confirm = RetrofitFactory.getApiService().queryPlan(version, requestNo, machineCode, account, signature, pageNum,cardNo,tranType,channelId);
            Observable<BaseBean> compose = confirm.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<FinanceDetailBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(FinanceDetailBean data) {
                    mView.getDataSuccess(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
