package com.rflash.magiccube.ui.creditrecharge;

import android.content.Context;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreditrechargeContract {
    interface View extends BaseView {
        void showQrcode(CreditRechare data);
    }

    // 充值次数
    interface  Presenter extends BasePresenter<View> {
        void rechargeTimes(String creditType, String num, String payType);
    }
}
