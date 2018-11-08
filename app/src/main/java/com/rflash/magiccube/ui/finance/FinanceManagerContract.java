package com.rflash.magiccube.ui.finance;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;

/**
 * Created by lenovo on 2018/10/20.
 */

public class FinanceManagerContract {
    public interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(Object msg);
        void getDataSuccess(Object response);
    }

    interface  Presenter extends BasePresenter<View> {
       //财务管理列表
        void queryReport(String cardSeqno, String cardNo, String customerNmae, String salesMan, String startDate, String endDate, String pageNum);

        //财务详情
        void queryPlan(String cardNo, String tranType, String channelId, String pageNum);
    }
}
