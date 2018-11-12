package com.rflash.magiccube.ui.cardmanager.addplan;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;

/**
 * Created by lenovo on 2018/10/27.
 */

public class AddPlanContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getResult();
        void queryShanghuResult(ShanghuBean response);
    }

    interface  Presenter extends BasePresenter<View> {

        // 添加规划记录
        void addPlan(String tranType, String cardNo, String channel, String merchantCode, String termCode, String amt, String isNeedT0, String date, String state);

        //查询商户
        void queryShanghu(String channelName, String merchantName, String merchantCode, String state, String merchantType, String startDate, String endDate, String bind, String pageNum,String pageSize);

    }
}
