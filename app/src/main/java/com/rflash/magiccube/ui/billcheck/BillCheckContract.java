package com.rflash.magiccube.ui.billcheck;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BillCheckContract {
    interface View extends BaseView {
        void getBillCheck(CreditShareItem userPortrait);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryBillCheck(String creditType, String pageNum);
    }
}
