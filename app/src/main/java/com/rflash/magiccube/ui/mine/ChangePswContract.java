package com.rflash.magiccube.ui.mine;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/7 11:49
 * @desc:
 */

public class ChangePswContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess();
    }

    interface  Presenter extends BasePresenter<View> {

        //
        void changePsw(String password, String newPassword);



    }
}
