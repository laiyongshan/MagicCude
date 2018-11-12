package com.rflash.magiccube.ui.newmain;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.main.AppInfo;
import com.rflash.magiccube.ui.main.BillCount;
import com.rflash.magiccube.ui.salesmen.SalesmenBean;

/**
 * Created by lenovo on 2018/11/1.
 */

public class NewMainContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void queryUpdateSuccess(AppInfo info);
        void queryDirtSuccess(DirtBean response);
        void querySalMenSuccess(SalesmenBean salesmenBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void updateApp();//更新App
        void queryDirt();//字典同步
        void querySalesMan(String name, String profitRatio);//业务员查询
    }
}
