package com.rflash.magiccube.ui.main;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class MainContract {
    interface View extends BaseView {
        void queryUpdateSuccess(AppInfo info);
        void getBillCount(BillCount billCount);
    }

    interface  Presenter extends BasePresenter<View> {
        void getBillCount();
        void updateApp();
    }
}
