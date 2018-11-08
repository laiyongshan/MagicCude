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

<<<<<<< HEAD
    interface  Presenter extends BasePresenter<View> {

        //
        void changePsw(String password, String newPassword);
=======
    interface  Presenter extends BasePresenter<ChangePswContract.View> {

        //
        void changePsw(String password,String newPassword);
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec



    }
}
