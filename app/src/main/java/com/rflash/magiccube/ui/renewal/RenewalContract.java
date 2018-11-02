package com.rflash.magiccube.ui.renewal;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;

/**
 * @author lys
 * @time 2018/11/2 14:38
 * @desc:
 */

public class RenewalContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void renewalResult(BaseBean respon );
    }

    interface  Presenter extends BasePresenter<RenewalContract.View> {

        //卡片续期
        void renewalCard(String cardNo);


    }
}
