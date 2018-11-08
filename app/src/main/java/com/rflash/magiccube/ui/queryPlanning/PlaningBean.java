package com.rflash.magiccube.ui.queryPlanning;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/6 16:38
 * @desc:
 */

public class PlaningBean extends BaseBean {

    private int totalNum;
    private int totalPage;
    private String amt;//总金额
    private String tranCost;//总成本
    private String notOperatorSale;//未消费总金额
    private String notOperatorSaleTranCost;//未消费成本
    private String dealSale;//已消费金额
    private String dealSaleTranCost;//已消费成本
    private String notOperatorRepay;//未还款金额
    private String dealRepay;//已还款金额

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getTranCost() {
        return tranCost;
    }

    public void setTranCost(String tranCost) {
        this.tranCost = tranCost;
    }

    public String getNotOperatorSale() {
        return notOperatorSale;
    }

    public void setNotOperatorSale(String notOperatorSale) {
        this.notOperatorSale = notOperatorSale;
    }

    public String getNotOperatorSaleTranCost() {
        return notOperatorSaleTranCost;
    }

    public void setNotOperatorSaleTranCost(String notOperatorSaleTranCost) {
        this.notOperatorSaleTranCost = notOperatorSaleTranCost;
    }

    public String getDealSale() {
        return dealSale;
    }

    public void setDealSale(String dealSale) {
        this.dealSale = dealSale;
    }

    public String getDealSaleTranCost() {
        return dealSaleTranCost;
    }

    public void setDealSaleTranCost(String dealSaleTranCost) {
        this.dealSaleTranCost = dealSaleTranCost;
    }

    public String getNotOperatorRepay() {
        return notOperatorRepay;
    }

    public void setNotOperatorRepay(String notOperatorRepay) {
        this.notOperatorRepay = notOperatorRepay;
    }

    public String getDealRepay() {
        return dealRepay;
    }

    public void setDealRepay(String dealRepay) {
        this.dealRepay = dealRepay;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    private List<PlaningBean.ResultBean> result;

    public List<PlaningBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<PlaningBean.ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * accountType : T1
         * amt : 120000
         * bankName : 平安银行
         * billType : REPAY
         * cardNo : 5268550416353118
         * cardSeqno : 69
         * channel : 37
         * channelName : 代付
         * channelState : valid
         * customerName : zeng
         * date : 2018-11-08 16:12:28
         * generateAmt : 120000
         * merchantBind : N
         * planId : 2018102900538517
         * planSource : SYSTEM
         * state : NOT_OPERATOR
         * syncStateName :
         * tranCost : 100
         * tranType : REPAY
         * merchantCode : 893440153116348
         * merchantMccSmallClass : 20
         * merchantMccSmallClassName : 日用百货超市类
         * merchantName : 广州市花都区狮岭大炜百货店
         * merchantState : valid
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
        private String planId;
        private String planSource;
        private String state;
        private String syncStateName;
        private String tranCost;
        private String tranType;
        private String merchantCode;
        private int merchantMccSmallClass;
        private String merchantMccSmallClassName;
        private String merchantName;
        private String merchantState;
        private String operatorTime;

        public void setOperatorTime(String operatorTime) {
            this.operatorTime = operatorTime;
        }

        public String getOperatorTime() {
            return operatorTime;
        }

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
    }
}
