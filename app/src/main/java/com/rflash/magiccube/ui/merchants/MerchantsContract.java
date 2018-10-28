package com.rflash.magiccube.ui.merchants;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.batchrepay.BatchRepayContract;

/**
 * Created by lenovo on 2018/10/16.
 */

public class MerchantsContract {

    interface View extends BaseView{
        void getDataFail(String msg);
        void showRefresh();
        void finishRefresh();
        void getDataSuccess(Object response);
        void getRemindDataSuccess(Object response);
    }

    interface  Presenter extends BasePresenter<MerchantsContract.View> {
        void getCount();//获取首页统计
        void getRemindCount();//首页通知数量统计
    }


}
