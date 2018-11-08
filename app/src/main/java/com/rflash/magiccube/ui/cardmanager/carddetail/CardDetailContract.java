package com.rflash.magiccube.ui.cardmanager.carddetail;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.CardManagerContract;

/**
 * Created by lenovo on 2018/10/24.
 */

public class CardDetailContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void updateCardState();
    }

    interface  Presenter extends BasePresenter<View> {

        //修改卡片状态
        void updateCardState(String cardNo, String state);
    }
}
