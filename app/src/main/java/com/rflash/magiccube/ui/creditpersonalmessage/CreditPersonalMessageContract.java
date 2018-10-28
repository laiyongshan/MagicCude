package com.rflash.magiccube.ui.creditpersonalmessage;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;


public class CreditPersonalMessageContract {
    interface View extends BaseView {
        void retuenUpActivity();
    }

    interface  Presenter extends BasePresenter<View> {
        void deletePersonalMessage(String orderNo);
    }
}
