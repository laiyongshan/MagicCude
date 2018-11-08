package com.rflash.magiccube.ui.cardmanager.increase;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargeContract;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargerBean;

/**
 * Created by lenovo on 2018/10/26.
 */

public class CardIncreaseContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(Object response);
        void increaseSuccess();
    }


    interface  Presenter extends BasePresenter<View> {
        // 查询卡片提额记录
        void queryLimitChange(String cardNo, String changeType, String pageNum);

        //添加提额记录
        void insertLimitChange(String cardNo, String time, String changeType, String amt, String createUser) ;
    }

}
