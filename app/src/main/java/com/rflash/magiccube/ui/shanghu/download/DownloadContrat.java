package com.rflash.magiccube.ui.shanghu.download;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;
import com.rflash.magiccube.ui.shanghu.ShanghuContract;

/**
 * Created by lenovo on 2018/10/20.
 */

public class DownloadContrat {
    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void bindFail(String msg);
        void bindSuccess(BaseBean response);
        void getDataSuccess(DownloadBean response);
    }

    interface  Presenter extends BasePresenter<DownloadContrat.View> {
        //商户下载（绑定商户）
        void bindShanghu(String merchant);

        //商户查询
        void queryShanghu(String channelName,String merchantName,String merchantCode,String state,String merchantType,String startDate,String endDate,String bind,String pageNum);
    }
}
