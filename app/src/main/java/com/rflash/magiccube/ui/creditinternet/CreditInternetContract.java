package com.rflash.magiccube.ui.creditinternet;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditInternetContract {
    interface View extends BaseView {
        void getCreditInternet(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryCreditInternet(String creditType, String pageNum);
    }
}
