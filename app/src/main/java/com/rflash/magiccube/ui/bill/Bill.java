package com.rflash.magiccube.ui.bill;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by yangfan on 2017/11/15.
 */

public class Bill extends BaseBean {

    /**
     * result : [{"bankName":"恒丰银行","billAmt":1,"billId":1,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","consumeAmt":101,"consumeNum":2,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511852990000,"repayAmt":1,"repayNum":1},{"billAmt":190000,"billId":7,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":3,"cardNo":"6214921223322222","cardSeqno":"000001","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"411321199011151856","customerName":"张小凡","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":190000,"billId":8,"billMonth":"2","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":1,"billId":12,"billMonth":"12","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":12,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":1,"billId":11,"billMonth":"11","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":95000,"billId":4,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20171106","billType":"REPAY","cardId":4,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":91000,"billId":6,"billMonth":"3","billPlanStartDate":"20171206","billStartDate":"20170916","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":1,"billId":10,"billMonth":"2","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":9,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":190000,"billId":9,"billMonth":"4","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":93000,"billId":3,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20171006","billType":"REPAY","cardId":2,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1}]
     * totalNum : 11
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
         * bankName : 恒丰银行
         * billAmt : 1
         * billId : 1
         * billMonth : 1
         * billPlanStartDate : 20171206
         * billStartDate : 20170906
         * billType : REPAY
         * cardId : 1
         * cardNo : 4367485015798608
         * cardSeqno : 1
         * consumeAmt : 101
         * consumeNum : 2
         * createTime : 1511427795000
         * customerID :
         * customerName :
         * isConfirm : N
         * isPlan : N
         * modifyTime : 1511852990000
         * repayAmt : 1
         * repayNum : 1
         * confirmUser : 1
         */

        private String bankName;
        private String billAmt;
        private int billId;
        private String billMonth;
        private String billPlanStartDate;
        private String billStartDate;
        private String billType;
        private int cardId;
        private String cardNo;
        private String cardSeqno;
        private int consumeAmt;
        private String consumeNum;
        private long createTime;
        private String customerID;
        private String customerName;
        private String isConfirm;
        private String isPlan;
        private long modifyTime;
        private int repayAmt;
        private int repayNum;
        private String confirmUser;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBillAmt() {
            return billAmt;
        }

        public void setBillAmt(String billAmt) {
            this.billAmt = billAmt;
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

        public String getBillPlanStartDate() {
            return billPlanStartDate;
        }

        public void setBillPlanStartDate(String billPlanStartDate) {
            this.billPlanStartDate = billPlanStartDate;
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

        public String getConsumeNum() {
            return consumeNum;
        }

        public void setConsumeNum(String consumeNum) {
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

        public String getConfirmUser() {
            return confirmUser;
        }

        public void setConfirmUser(String confirmUser) {
            this.confirmUser = confirmUser;
        }
    }
}
