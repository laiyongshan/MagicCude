package com.rflash.magiccube.ui.notify;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/12 09:48
 * @desc:
 */

public class NotifyContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(NotifyBean response);
    }


    interface  Presenter extends BasePresenter<View>{
        void getNotifyData(String pageNum, String pageSize);
    }


}
