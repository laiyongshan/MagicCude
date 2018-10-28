package com.rflash.magiccube.ui.workreport;

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

public class WorkReportPresenter extends BasePresenterImpl<WorkReportContract.View> implements WorkReportContract.Presenter{

    /**
     * 统计报表请求
     */
    @Override
    public void threeDayCount() {
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

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> threeDayCount = RetrofitFactory.getApiService().threeDayCount(version, requestNo, machineCode, account, signature);
            Observable<BaseBean> compose = threeDayCount.compose(((BaseActivity) mView).compose(((BaseActivity) mView).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<ThreeDayCount>((BaseActivity) mView) {
                @Override
                protected void onSuccess(ThreeDayCount data) {
                     mView.getThreeDayCount(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
