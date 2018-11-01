package com.rflash.magiccube.ui.refund;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class RefundBean extends BaseBean {

    /**
     * result : [{"confirm":"Y","billAmt":90000,"cardSeqno":"65","billStartDate":"20181008","bankName":"华夏银行","noRepayAmt":88000,"repayState":"NO_REPAID","billEndDate":"20181102","cardNo":"6259691131986953","customerName":"zeng"},{"confirm":"Y","billAmt":404500,"cardSeqno":"69","billStartDate":"20181020","bankName":"平安银行","noRepayAmt":286500,"repayState":"NO_REPAID","billEndDate":"20181108","cardNo":"5268550416353118","customerName":"zeng"},{"confirm":"Y","billAmt":90000,"cardSeqno":"74","billStartDate":"20181023","bankName":"华夏银行","noRepayAmt":90000,"repayState":"NO_REPAID","billEndDate":"20181110","cardNo":"6259691131986193","customerName":"zeng"},{"confirm":"Y","billAmt":228906,"cardSeqno":"68","billStartDate":"20181014","bankName":"招商银行","noRepayAmt":-917,"repayState":"REPAID","billEndDate":"20181102","cardNo":"4392268333819671","customerName":"zeng"}]
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
         * confirm : Y
         * billAmt : 90000
         * cardSeqno : 65
         * billStartDate : 20181008
         * bankName : 华夏银行
         * noRepayAmt : 88000
         * repayState : NO_REPAID
         * billEndDate : 20181102
         * cardNo : 6259691131986953
         * customerName : zeng
         */

        private String confirm;
        private int billAmt;
        private String cardSeqno;
        private String billStartDate;
        private String bankName;
        private int noRepayAmt;
        private String repayState;
        private String billEndDate;
        private String cardNo;
        private String customerName;

        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(String confirm) {
            this.confirm = confirm;
        }

        public int getBillAmt() {
            return billAmt;
        }

        public void setBillAmt(int billAmt) {
            this.billAmt = billAmt;
        }

        public String getCardSeqno() {
            return cardSeqno;
        }

        public void setCardSeqno(String cardSeqno) {
            this.cardSeqno = cardSeqno;
        }

        public String getBillStartDate() {
            return billStartDate;
        }

        public void setBillStartDate(String billStartDate) {
            this.billStartDate = billStartDate;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getNoRepayAmt() {
            return noRepayAmt;
        }

        public void setNoRepayAmt(int noRepayAmt) {
            this.noRepayAmt = noRepayAmt;
        }

        public String getRepayState() {
            return repayState;
        }

        public void setRepayState(String repayState) {
            this.repayState = repayState;
        }

        public String getBillEndDate() {
            return billEndDate;
        }

        public void setBillEndDate(String billEndDate) {
            this.billEndDate = billEndDate;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }
}
