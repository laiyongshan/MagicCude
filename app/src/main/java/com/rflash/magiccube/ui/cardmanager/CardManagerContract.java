package com.rflash.magiccube.ui.cardmanager;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;

/**
 * Created by lenovo on 2018/10/23.
 */

public class CardManagerContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(CardBean response);
    }

    interface  Presenter extends BasePresenter<View> {
       //queryCard 查询卡片信息
        void queryCard(String cardNo, String cardSeqno, String salesMan, String billDate, String repayDate, String state, String pageNum, String count);
    }
}
