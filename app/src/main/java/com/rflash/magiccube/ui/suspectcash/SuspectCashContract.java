package com.rflash.magiccube.ui.suspectcash;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;
import com.rflash.magiccube.ui.userportrait.UserPortraitContract;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SuspectCashContract {
    interface View extends BaseView {
        void getSuspectCash(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void querySuspectCash(String creditType, String pageNum);
    }
}
