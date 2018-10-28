package com.rflash.magiccube.ui.credithandle;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.bill.Bill;

/**
 *  信用卡办理界面Contract
 *  Created by Guobaihui on 2018/03/19.
 */

public class CreditHandleContract {
    interface View extends BaseView {
        void getHandleLink(CardLink cardLink);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryLink(String type);
    }
}
