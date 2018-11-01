package com.rflash.magiccube.ui.billconfirm;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/10.
 */

public class BillConfirmBean extends BaseBean {


    /**
     * result : [{"bankName":"华夏银行","billAmt":90000,"billEndDate":"20181110","billMonth":"201810","billStartDate":"20181023","billType":"REPAY","cardId":4194,"cardNo":"6259691131986193","cardSeqno":"74","createTime":1540346297000,"customerName":"zeng","endTime":1541865600000,"operatorTime":1540346309000,"operatorUser":"zengyan","remindType":"BILL_CONFIRM","state":"DEAL"},{"bankName":"平安银行","billAmt":404500,"billEndDate":"20181108","billMonth":"201810","billStartDate":"20181020","billType":"REPAY","cardId":4018,"cardNo":"5268550416353118","cardSeqno":"69","createTime":1539972960000,"customerName":"zeng","endTime":1541692800000,"operatorTime":1540192978000,"operatorUser":"zengyan","remindType":"BILL_CONFIRM","state":"DEAL"},{"bankName":"招商银行","billAmt":228888,"billEndDate":"20181102","billMonth":"201810","billStartDate":"20181014","billType":"REPAY","cardId":3933,"cardNo":"4392268333819671","cardSeqno":"68","createTime":1539453672000,"customerName":"zeng","endTime":1541174400000,"operatorTime":1539569714000,"operatorUser":"zengyan","remindType":"BILL_CONFIRM","state":"DEAL"},{"bankName":"华夏银行","billAmt":90000,"billEndDate":"20181102","billMonth":"201810","billStartDate":"20181008","billType":"REPAY","cardId":4117,"cardNo":"6259691131986953","cardSeqno":"65","createTime":1539152429000,"customerName":"zeng","endTime":1541174400000,"operatorTime":1539152439000,"operatorUser":"zengyan","remindType":"BILL_CONFIRM","state":"DEAL"}]
     * totalNum : 4
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
         * bankName : 华夏银行
         * billAmt : 90000
         * billEndDate : 20181110
         * billMonth : 201810
         * billStartDate : 20181023
         * billType : REPAY
         * cardId : 4194
         * cardNo : 6259691131986193
         * cardSeqno : 74
         * createTime : 1540346297000
         * customerName : zeng
         * endTime : 1541865600000
         * operatorTime : 1540346309000
         * operatorUser : zengyan
         * remindType : BILL_CONFIRM
         * state : DEAL
         */

        private String bankName;
        private int billAmt;
        private String billEndDate;
        private String billMonth;
        private String billStartDate;
        private String billType;
        private int cardId;
        private String cardNo;
        private String cardSeqno;
        private long createTime;
        private String customerName;
        private long endTime;
        private long operatorTime;
        private String operatorUser;
        private String remindType;
        private String state;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBillAmt() {
            return billAmt;
        }

        public void setBillAmt(int billAmt) {
            this.billAmt = billAmt;
        }

        public String getBillEndDate() {
            return billEndDate;
        }

        public void setBillEndDate(String billEndDate) {
            this.billEndDate = billEndDate;
        }

        public String getBillMonth() {
            return billMonth;
        }

        public void setBillMonth(String billMonth) {
            this.billMonth = billMonth;
        }

        public String getBillStartDate() {
            return billStartDate;
        }

        public void setBillStartDate(String billStartDate) {
            this.billStartDate = billStartDate;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getOperatorTime() {
            return operatorTime;
        }

        public void setOperatorTime(long operatorTime) {
            this.operatorTime = operatorTime;
        }

        public String getOperatorUser() {
            return operatorUser;
        }

        public void setOperatorUser(String operatorUser) {
            this.operatorUser = operatorUser;
        }

        public String getRemindType() {
            return remindType;
        }

        public void setRemindType(String remindType) {
            this.remindType = remindType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
