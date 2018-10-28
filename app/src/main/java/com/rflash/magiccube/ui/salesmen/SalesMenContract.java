package com.rflash.magiccube.ui.salesmen;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.merchants.MerchantsContract;

/**
 * Created by lenovo on 2018/10/16.
 */

public class SalesMenContract  {

    interface View extends BaseView {
        void getDataFail(String msg);
        void showRefresh();
        void finishRefresh();
        void getDataSuccess(Object response);
        void deleteSalesManSuccess(Object response);
        void operaSalesManSuccess(Object response);
    }

    interface  Presenter extends BasePresenter<SalesMenContract.View> {
        void operaSalesMan(String id,String flag,String name,String profitRatio);//业务员新增/修改
        void deleteSalesMan(String id,String name);//业务员删除
        void querySalesMan(String name,String profitRatio);//业务员查询
    }

}
