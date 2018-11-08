package com.rflash.magiccube.ui.renewalmind;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/2 14:38
 * @desc:
 */

public class RenewalMindContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(RenewalMindBean renewalMindBean);
    }

<<<<<<< HEAD
    interface  Presenter extends BasePresenter<View> {
=======
    interface  Presenter extends BasePresenter<RenewalMindContract.View> {
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

        //
        void getRenewalList(String pageNum);



    }
}
