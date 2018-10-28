package com.rflash.magiccube.ui.creditqueryitem;

import android.content.Context;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditQueryItemContract {
    interface View extends BaseView {
        void finishActivity();
    }

    interface  Presenter extends BasePresenter<View> {
        void queryProduct(String name, String identityCard, String cardNo, String mobile, String beginDate, String endDate, String creditType);
    }
}
