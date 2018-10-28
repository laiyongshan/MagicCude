package com.rflash.magiccube.ui.userportrait;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserPortraitContract {
    interface View extends BaseView {
        void getUserPortrait(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryUserPortrait(String creditType, String pageNum);
    }
}
