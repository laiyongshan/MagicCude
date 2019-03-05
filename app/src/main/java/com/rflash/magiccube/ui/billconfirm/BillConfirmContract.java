package com.rflash.magiccube.ui.billconfirm;

import com.rflash.magiccube.mvp.BasePresenter;
import com.rflash.magiccube.mvp.BaseView;
import com.rflash.magiccube.ui.cardmanager.cardbill.CardBillBean;

/**
 * Created by lenovo on 2018/10/18.
 */

public class BillConfirmContract {

    interface View extends BaseView {
        void showRefresh();
        void finishRefresh();
        void getDataFail(String msg);
        void getDataSuccess(BillConfirmBean response);
        void updateCardBillSuccess();
        void confirmCardBillSuccess();
        void ignoreCardBillSuccess();
        void queryBillDataSuccess(CardBillBean cardBillBean);//查询卡片账单信息

    }

    interface  Presenter extends BasePresenter<View> {

        //queryCard 查询卡片账单信息
        void accurateQueryBill(String cardNo, String billMonth, String available, String isConfirm);


        //获取账单确认列表
        void getBillConfirmList(String pageNum,String pageSize);

        //修改账单金额
        void updateCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate);

        //确认账单
        void comfirmCardBill(String billId, String cardNo, String billMonth, String billAmt, String operate);

        //忽略账单
        void ignoreCardBill(String cardNo, String remindType);

    }

}
