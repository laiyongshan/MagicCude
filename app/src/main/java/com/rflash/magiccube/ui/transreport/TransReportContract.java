package com.rflash.magiccube.ui.transreport;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TransReportContract {
    interface View extends BaseView {
        void getTransReport(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryTransReport(String creditType, String pageNum);
    }
}
