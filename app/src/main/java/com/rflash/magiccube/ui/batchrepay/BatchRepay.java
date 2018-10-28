package com.rflash.magiccube.ui.batchrepay;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by yangfan on 2017/11/20.
 */

public class BatchRepay extends BaseBean{

    /**
     * result : [{"accountType":"T0","amt":"20010","bankName":"恒丰银行","cardNo":"4367485015798608","cardSeqno":"1","channelName":"测试渠道","customerName":"","date":"2017-11-29 00:00:00","merchantCode":"0987654321","merchantName":"商户二期pos商户","modifyTime":"2017-11-28 14:57:16","operatorTime":"2017-11-28 14:57:16","planId":"2017112800000054","planSource":"INPUT","state":"NOT_OPERATOR","tranCost":"701","tranType":"REPAY"}]
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
         * accountType : T0
         * amt : 20010
         * bankName : 恒丰银行
         * cardNo : 4367485015798608
         * cardSeqno : 1
         * channelName : 测试渠道
         * customerName :
         * date : 2017-11-29 00:00:00
         * merchantCode : 0987654321
         * merchantName : 商户二期pos商户
         * modifyTime : 2017-11-28 14:57:16
         * operatorTime : 2017-11-28 14:57:16
         * planId : 2017112800000054
         * planSource : INPUT
         * state : NOT_OPERATOR
         * tranCost : 701
         * tranType : REPAY
         */

        private String accountType;
        private String amt;
        private String bankName;
        private String cardNo;
        private String cardSeqno;
        private String channelName;
        private String customerName;
        private String date;
        private String merchantCode;
        private String merchantName;
        private String modifyTime;
        private String operatorTime;
        private String planId;
        private String planSource;
        private String state;
        private String tranCost;
        private String tranType;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
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

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
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

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
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
