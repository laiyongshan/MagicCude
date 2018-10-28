package com.rflash.magiccube.ui.cardmanager.addplan;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.cardbase.BaseInfoBean;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInfoContract;

/**
 * Created by lenovo on 2018/10/27.
 */

public class AddPlanContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getResult(BaseBean respon );
    }

    interface  Presenter extends BasePresenter<AddPlanContract.View> {

        // 添加规划记录
        void addPlan(String tranType,String cardNo,String channel,String merchantCode,String termCode,String amt,String isNeedT0,String date,String state);


    }
}
