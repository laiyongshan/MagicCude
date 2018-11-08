package com.rflash.magiccube.ui.login;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess(String name, String pwd);
    }

    interface  Presenter extends BasePresenter<View> {
        void saveUserInfo(boolean checked, String name, String pwd);
        void login(String version, String machineCode, String account, String password);
    }
}
