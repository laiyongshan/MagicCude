package com.rflash.magiccube.ui.cardmanager;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/12.
 */

public class CardBean extends BaseBean {

    /**
     * availableAmt : 490000
     * fixedLimit : 4490000
     * initAmt : 500000
     * result : [{"availableAmt":490000,"billDate":"1","cardBankName":"招商银行","cardId":1996,"cardMedia":"IC_CARD","cardNo":"4392250810275866","cardSeqno":"A01","customerID":"4322222222222222222","customerName":"何海华","fixedLimit":4490000,"initAmt":500000,"phone":"18555555552","pointId":154,"pointName":"测试","repayDate":"20","repayDateType":"FIXED","salesMan":45,"serviceEndDate":"20180630","state":"EXPIRE","verifyState":"N"}]
     * totalNum : 1
     * totalPage : 1
     */

    private int availableAmt;
    private int fixedLimit;
    private int initAmt;
    private int totalNum;
    private int totalPage;
    private List<ResultBean> result;

    public int getAvailableAmt() {
        return availableAmt;
    }

    public void setAvailableAmt(int availableAmt) {
        this.availableAmt = availableAmt;
    }

    public int getFixedLimit() {
        return fixedLimit;
    }

    public void setFixedLimit(int fixedLimit) {
        this.fixedLimit = fixedLimit;
    }

    public int getInitAmt() {
        return initAmt;
    }

    public void setInitAmt(int initAmt) {
        this.initAmt = initAmt;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean extends BaseBean{
        /**
         * availableAmt : 490000
         * billDate : 1
         * cardBankName : 招商银行
         * cardId : 1996
         * cardMedia : IC_CARD
         * cardNo : 4392250810275866
         * cardSeqno : A01
         * customerID : 4322222222222222222
         * customerName : 何海华
         * fixedLimit : 4490000
         * initAmt : 500000
         * phone : 18555555552
         * pointId : 154
         * pointName : 测试
         * repayDate : 20
         * repayDateType : FIXED
         * salesMan : 45
         * serviceEndDate : 20180630
         * state : EXPIRE
         * verifyState : N
         */

        private int availableAmt;
        private String billDate;
        private String cardBankName;
        private int cardId;
        private String cardMedia;
        private String cardNo;
        private String cardSeqno;
        private String customerID;
        private String customerName;
        private int fixedLimit;
        private int initAmt;
        private String phone;
        private int pointId;
        private String pointName;
        private String repayDate;
        private String repayDateType;
        private int salesMan;
        private String serviceEndDate;
        private String state;
        private String verifyState;

        public int getAvailableAmt() {
            return availableAmt;
        }

        public void setAvailableAmt(int availableAmt) {
            this.availableAmt = availableAmt;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getCardBankName() {
            return cardBankName;
        }

        public void setCardBankName(String cardBankName) {
            this.cardBankName = cardBankName;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public String getCardMedia() {
            return cardMedia;
        }

        public void setCardMedia(String cardMedia) {
            this.cardMedia = cardMedia;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardSeqno() {
            return cardSeqno;
        }

        public void setCardSeqno(String cardSeqno) {
            this.cardSeqno = cardSeqno;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getFixedLimit() {
            return fixedLimit;
        }

        public void setFixedLimit(int fixedLimit) {
            this.fixedLimit = fixedLimit;
        }

        public int getInitAmt() {
            return initAmt;
        }

        public void setInitAmt(int initAmt) {
            this.initAmt = initAmt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPointId() {
            return pointId;
        }

        public void setPointId(int pointId) {
            this.pointId = pointId;
        }

        public String getPointName() {
            return pointName;
        }

        public void setPointName(String pointName) {
            this.pointName = pointName;
        }

        public String getRepayDate() {
            return repayDate;
        }

        public void setRepayDate(String repayDate) {
            this.repayDate = repayDate;
        }

        public String getRepayDateType() {
            return repayDateType;
        }

        public void setRepayDateType(String repayDateType) {
            this.repayDateType = repayDateType;
        }

        public int getSalesMan() {
            return salesMan;
        }

        public void setSalesMan(int salesMan) {
            this.salesMan = salesMan;
        }

        public String getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(String serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getVerifyState() {
            return verifyState;
        }

        public void setVerifyState(String verifyState) {
            this.verifyState = verifyState;
        }
    }
}
