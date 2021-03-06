package com.rflash.magiccube.ui.queryPlanning;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/6 16:37
 * @desc:
 */

public class QueryPlanningContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(Object msg);
        void getDataSuccess(PlaningBean response);
        void syncPlanSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        //规划查询
        void queryPlan(String startDate, String endDate, String cardSeqno, String customerName, String cardNo, String state, String tranType, String accountType, String syncState, String pageNum,String count);

        //同步规划
        void syncPlan();
    }
}
