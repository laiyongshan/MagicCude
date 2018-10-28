package com.rflash.magiccube.ui.creditapplication;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditApplicationContract {
    interface View extends BaseView {
        void getCreditApplication(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryCreditApplication(String creditType, String pageNum);
    }
}
