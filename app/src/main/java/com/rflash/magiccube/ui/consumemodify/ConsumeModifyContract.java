package com.rflash.magiccube.ui.consumemodify;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class ConsumeModifyContract {
    interface View extends BaseView {
        void getChannel(Channel channel);

        void getMerchant(Merchant merchant,String highLightText);

        void modifySuccess();

    }

    interface Presenter extends BasePresenter<View> {
        void queryChannel();

        void queryMerchant(String channel, String merchantName,String pageNum);

        void modify(String planId, String amt, String merchantCode, String channel);
    }
}
