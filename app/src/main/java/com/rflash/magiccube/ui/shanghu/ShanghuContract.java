package com.rflash.magiccube.ui.shanghu;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.refund.RefundBean;
import com.rflash.magiccube.ui.refund.RefundContract;

/**
 * Created by lenovo on 2018/10/20.
 */

public class ShanghuContract {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(ShanghuBean response);
        void unbindLoading();
        void unbindError(String msg);
        void unbindSuccess();
        void unbindFinish();
    }

    interface  Presenter extends BasePresenter<View> {
        //查询商户
        void queryShanghu(String channelName, String merchantName, String merchantCode, String state, String merchantType, String startDate, String endDate, String bind, String pageNum);

        //删除商户
        /**
         * 格式：
         * 渠道号|商户号
         * 多个的时候逗号隔开
         * 例：
         * 1|123,1|456,2|789
         * */
        void unbindShanghu(String merchant);
    }
}
