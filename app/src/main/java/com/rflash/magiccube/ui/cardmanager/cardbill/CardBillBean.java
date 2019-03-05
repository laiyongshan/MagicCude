package com.rflash.magiccube.ui.cardmanager.cardbill;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class CardBillBean extends BaseBean {


    /**
     * result : [{"bankName":"华夏银行","billAmt":90000,"billEndDate":"20181110","billId":25508,"billMonth":"201810","billStartDate":"20181023","billType":"REPAY","cardId":4194,"cardNo":"6259691131986193","cardSeqno":"74","consumeAmt":0,"consumeNum":0,"createTime":1540346297000,"customerID":"200104166204060512","customerName":"zeng","isConfirm":"Y","isPlan":"Y","modifyTime":1541698109000,"origBillAmt":90000,"repayAmt":0,"repayNum":0,"state":"VALID"}]
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
         * bankName : 华夏银行
         * billAmt : 90000
         * billEndDate : 20181110
         * billId : 25508
         * billMonth : 201810
         * billStartDate : 20181023
         * billType : REPAY
         * cardId : 4194
         * cardNo : 6259691131986193
         * cardSeqno : 74
         * consumeAmt : 0
         * consumeNum : 0
         * createTime : 1540346297000
         * customerID : 200104166204060512
         * customerName : zeng
         * isConfirm : Y
         * isPlan : Y
         * modifyTime : 1541698109000
         * origBillAmt : 90000
         * repayAmt : 0
         * repayNum : 0
         * state : VALID
         */

        private String bankName;
        private int billAmt;
        private String billEndDate;
        private int billId;
        private String billMonth;
        private String billStartDate;
        private String billType;
        private int cardId;
        private String cardNo;
        private String cardSeqno;
        private int consumeAmt;
        private int consumeNum;
        private long createTime;
        private String customerID;
        private String customerName;
        private String isConfirm;
        private String isPlan;
        private long modifyTime;
        private int origBillAmt;
        private int repayAmt;
        private int repayNum;
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

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
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

        public int getConsumeAmt() {
            return consumeAmt;
        }

        public void setConsumeAmt(int consumeAmt) {
            this.consumeAmt = consumeAmt;
        }

        public int getConsumeNum() {
            return consumeNum;
        }

        public void setConsumeNum(int consumeNum) {
            this.consumeNum = consumeNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public String getIsConfirm() {
            return isConfirm;
        }

        public void setIsConfirm(String isConfirm) {
            this.isConfirm = isConfirm;
        }

        public String getIsPlan() {
            return isPlan;
        }

        public void setIsPlan(String isPlan) {
            this.isPlan = isPlan;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getOrigBillAmt() {
            return origBillAmt;
        }

        public void setOrigBillAmt(int origBillAmt) {
            this.origBillAmt = origBillAmt;
        }

        public int getRepayAmt() {
            return repayAmt;
        }

        public void setRepayAmt(int repayAmt) {
            this.repayAmt = repayAmt;
        }

        public int getRepayNum() {
            return repayNum;
        }

        public void setRepayNum(int repayNum) {
            this.repayNum = repayNum;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
