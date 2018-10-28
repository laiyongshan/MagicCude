package com.rflash.magiccube.ui.cardmanager;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.http.DefaultObserver;
import com.rflash.magiccube.http.RetrofitFactory;
import com.rflash.magiccube.mvp.BasePresenterImpl;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;
import com.rflash.magiccube.util.SignUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/10/23.
 */

public class CardManagerPresenter extends BasePresenterImpl<CardManagerContract.View> implements CardManagerContract.Presenter  {
    /**
     * count:Y 进行数据统计
     * */
    @Override
    public void queryCard(String cardNo, String cardSeqno, String salesMan, String billDate, String repayDate, String state, String pageNum, String count) {
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
        treeMap.put("cardSeqno",cardSeqno);
        treeMap.put("salesMan",salesMan);
        treeMap.put("state",state);
        treeMap.put("billDate",billDate);
        treeMap.put("repayDate",repayDate);
        treeMap.put("state",state);
        treeMap.put("count",count);

        try {
            signature = SignUtil.signDataWithStr(treeMap, SpUtil.getString(mView.getContext(), Config.USER_PRVKEY, ""));
            Observable<BaseBean> shanghu = RetrofitFactory.getApiService().queryCard(version, requestNo, machineCode, account, signature, pageNum, cardNo,cardSeqno,salesMan,billDate,repayDate,state,count);
            Observable<BaseBean> compose = shanghu.compose(((BaseActivity) mView.getContext()).compose(((BaseActivity) mView.getContext()).<BaseBean>bindToLifecycle()));
            compose.subscribe(new DefaultObserver<CardBean>((BaseActivity) mView.getContext()) {
                @Override
                public void onError(Throwable e) {
                    mView.getDataFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.finishRefresh();
                }


                @Override
                protected void onSuccess(CardBean data) {
                    mView.getDataSuccess(data);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
