package com.rflash.magiccube.ui.finance.financedetail;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */

public class FinanceDetailBean extends BaseBean {


    /**
     * result : [{"accountType":"T0","amt":"1500","bankName":"华夏银行","billType":"REPAY","cardNo":"6259691131986953","cardSeqno":"65","channel":"38","channelName":"盛01","channelState":"valid","customerName":"zeng","date":"2018-10-17 00:00:00","generateAmt":"1500","merchantBind":"N","merchantCode":"893813554620338","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"广州市增城大拇指面包店","merchantState":"valid","modifyTime":"2018-10-17 15:39:00","operatorTime":"2018-10-17 15:39:00","planId":"2018101700512836","planSource":"INPUT","state":"DEAL","syncStateName":"","tranCost":"109","tranType":"SALE"},{"accountType":"T0","amt":"100","bankName":"华夏银行","cardNo":"6259691131986953","cardSeqno":"65","channel":"0","channelName":"自有POS","channelState":"freeze","customerName":"zeng","date":"2017-11-01 00:00:00","generateAmt":"100","merchantBind":"N","merchantCode":"1","modifyTime":"2018-11-03 10:54:56","operatorTime":"2018-11-03 10:54:56","planId":"2018110300546258","planSource":"INPUT","state":"DEAL","syncStateName":"","tranCost":"102","tranType":"SALE"}]
     * totalNum : 12
     * totalPage : 2
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
         * accountType : T0
         * amt : 1500
         * bankName : 华夏银行
         * billType : REPAY
         * cardNo : 6259691131986953
         * cardSeqno : 65
         * channel : 38
         * channelName : 盛01
         * channelState : valid
         * customerName : zeng
         * date : 2018-10-17 00:00:00
         * generateAmt : 1500
         * merchantBind : N
         * merchantCode : 893813554620338
         * merchantMccSmallClass : 20
         * merchantMccSmallClassName : 日用百货超市类
         * merchantName : 广州市增城大拇指面包店
         * merchantState : valid
         * modifyTime : 2018-10-17 15:39:00
         * operatorTime : 2018-10-17 15:39:00
         * planId : 2018101700512836
         * planSource : INPUT
         * state : DEAL
         * syncStateName :
         * tranCost : 109
         * tranType : SALE
         */

        private String accountType;
        private String amt;
        private String bankName;
        private String billType;
        private String cardNo;
        private String cardSeqno;
        private String channel;
        private String channelName;
        private String channelState;
        private String customerName;
        private String date;
        private String generateAmt;
        private String merchantBind;
        private String merchantCode;
        private int merchantMccSmallClass;
        private String merchantMccSmallClassName;
        private String merchantName;
        private String merchantState;
        private String modifyTime;
        private String operatorTime;
        private String planId;
        private String planSource;
        private String state;
        private String syncStateName;
        private String tranCost;
        private String tranType;

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getAmt() {
            return amt;
        }

        public void setAmt(String amt) {
            this.amt = amt;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
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

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelState() {
            return channelState;
        }

        public void setChannelState(String channelState) {
            this.channelState = channelState;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGenerateAmt() {
            return generateAmt;
        }

        public void setGenerateAmt(String generateAmt) {
            this.generateAmt = generateAmt;
        }

        public String getMerchantBind() {
            return merchantBind;
        }

        public void setMerchantBind(String merchantBind) {
            this.merchantBind = merchantBind;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public int getMerchantMccSmallClass() {
            return merchantMccSmallClass;
        }

        public void setMerchantMccSmallClass(int merchantMccSmallClass) {
            this.merchantMccSmallClass = merchantMccSmallClass;
        }

        public String getMerchantMccSmallClassName() {
            return merchantMccSmallClassName;
        }

        public void setMerchantMccSmallClassName(String merchantMccSmallClassName) {
            this.merchantMccSmallClassName = merchantMccSmallClassName;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantState() {
            return merchantState;
        }

        public void setMerchantState(String merchantState) {
            this.merchantState = merchantState;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getOperatorTime() {
            return operatorTime;
        }

        public void setOperatorTime(String operatorTime) {
            this.operatorTime = operatorTime;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getPlanSource() {
            return planSource;
        }

        public void setPlanSource(String planSource) {
            this.planSource = planSource;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSyncStateName() {
            return syncStateName;
        }

        public void setSyncStateName(String syncStateName) {
            this.syncStateName = syncStateName;
        }

        public String getTranCost() {
            return tranCost;
        }

        public void setTranCost(String tranCost) {
            this.tranCost = tranCost;
        }

        public String getTranType() {
            return tranType;
        }

        public void setTranType(String tranType) {
            this.tranType = tranType;
        }
    }
}
