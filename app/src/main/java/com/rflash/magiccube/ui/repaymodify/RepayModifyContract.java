package com.rflash.magiccube.ui.repaymodify;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class RepayModifyContract {
    interface View extends BaseView {
        void modifySuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void modify(String planId,String amt);
    }
}
