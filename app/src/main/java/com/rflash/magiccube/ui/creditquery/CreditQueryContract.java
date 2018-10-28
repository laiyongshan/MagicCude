package com.rflash.magiccube.ui.creditquery;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.bill.Bill;

/**
 *  信用查询界面Contract
 *  Created by Guobaihui on 2018/03/12.
 */

public class CreditQueryContract {
    interface View extends BaseView {
        void getCreditList(CreditQuery creditQuery);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryCredit();
    }
}
