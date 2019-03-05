package com.rflash.magiccube.ui.notify;

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
 * @time 2018/11/12 09:48
 * @desc:
 */

public class NotifyPresenter extends BasePresenterImpl<NotifyContract.View> implements NotifyContract.Presenter{

    //获取公告列表
    @Override
    public void getNotifyData(String pageNum, String pageSize) {
        String signature;
        String version = Config.VERSION_CODE;
        String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String machineCode = SpUtil.getString(mView.getContext(), Config.MACHINECODE, "");
        String account = SpUtil.getString(mView.getContext(), Config.ACCOUNT, "");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("version", version);
        treeMap.put("requestNo", requestNo);
        treeMap.put("machineCode", machineCode);
        treeMap.put("account", account);
        treeMap.put("pageNum",pageNum);
        treeMap.put("pageSize",pageSize);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> increase = RetrofitFactory.getApiService().queryNotice(version, requestNo, machineCode, account, signature, pageNum,pageSize);
            Observable<BaseBean> compose = increase.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<NotifyBean>((BaseActivity) mView.getContext()) {
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
                protected void onSuccess(NotifyBean data) {
                    mView.getDataSuccess(data);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
