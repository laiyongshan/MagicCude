package com.rflash.magiccube.ui.cardmanager.cardcharge;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardChargerBean extends BaseBean {

    /**
     * result : [{"cardId":1996,"operatorUser":"ceshi","paidAmt":67500,"paidTime":1524815698000,"paidType":"CASH","reciveAmt":67500,"serviceAmt":13500000,"serviceEndDate":"20180630","serviceRate":"0.0150","serviceStartDate":"20180427","serviceType":"FIXED_LIMIT"}]
     * totalNum : 1
     * totalPage : 1
     */

    private int totalNum;
    private int totalPage;
    private List<ResultBean> result;

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

    public static class ResultBean {
        /**
         * cardId : 1996
         * operatorUser : ceshi
         * paidAmt : 67500
         * paidTime : 1524815698000
         * paidType : CASH
         * reciveAmt : 67500
         * serviceAmt : 13500000
         * serviceEndDate : 20180630
         * serviceRate : 0.0150
         * serviceStartDate : 20180427
         * serviceType : FIXED_LIMIT
         */

        private int cardId;
        private String operatorUser;
        private int paidAmt;
        private long paidTime;
        private String paidType;
        private int reciveAmt;
        private int serviceAmt;
        private String serviceEndDate;
        private String serviceRate;
        private String serviceStartDate;
        private String serviceType;

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public String getOperatorUser() {
            return operatorUser;
        }

        public void setOperatorUser(String operatorUser) {
            this.operatorUser = operatorUser;
        }

        public int getPaidAmt() {
            return paidAmt;
        }

        public void setPaidAmt(int paidAmt) {
            this.paidAmt = paidAmt;
        }

        public long getPaidTime() {
            return paidTime;
        }

        public void setPaidTime(long paidTime) {
            this.paidTime = paidTime;
        }

        public String getPaidType() {
            return paidType;
        }

        public void setPaidType(String paidType) {
            this.paidType = paidType;
        }

        public int getReciveAmt() {
            return reciveAmt;
        }

        public void setReciveAmt(int reciveAmt) {
            this.reciveAmt = reciveAmt;
        }

        public int getServiceAmt() {
            return serviceAmt;
        }

        public void setServiceAmt(int serviceAmt) {
            this.serviceAmt = serviceAmt;
        }

        public String getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(String serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public String getServiceRate() {
            return serviceRate;
        }

        public void setServiceRate(String serviceRate) {
            this.serviceRate = serviceRate;
        }

        public String getServiceStartDate() {
            return serviceStartDate;
        }

        public void setServiceStartDate(String serviceStartDate) {
            this.serviceStartDate = serviceStartDate;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }
    }
}
