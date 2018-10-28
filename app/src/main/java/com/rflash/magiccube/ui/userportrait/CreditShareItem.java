package com.rflash.magiccube.ui.userportrait;

import com.rflash.magiccube.http.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guobaihui on 2018/03/15.
 */

public class CreditShareItem extends BaseBean implements Serializable{

    /**
     * result : [{"bankName":"恒丰银行","billAmt":1,"billId":1,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","consumeAmt":101,"consumeNum":2,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511852990000,"repayAmt":1,"repayNum":1},{"billAmt":190000,"billId":7,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":3,"cardNo":"6214921223322222","cardSeqno":"000001","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"411321199011151856","customerName":"张小凡","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":190000,"billId":8,"billMonth":"2","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":1,"billId":12,"billMonth":"12","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":12,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":1,"billId":11,"billMonth":"11","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":95000,"billId":4,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20171106","billType":"REPAY","cardId":4,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":91000,"billId":6,"billMonth":"3","billPlanStartDate":"20171206","billStartDate":"20170916","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":1,"billId":10,"billMonth":"2","billPlanStartDate":"20171206","billStartDate":"20170906","billType":"REPAY","cardId":9,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"bankName":"恒丰银行","billAmt":190000,"billId":9,"billMonth":"4","billPlanStartDate":"20171206","billStartDate":"20170816","billType":"REPAY","cardId":1,"cardNo":"4367485015798608","cardSeqno":"1","confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"customerID":"","customerName":"","isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1},{"billAmt":93000,"billId":3,"billMonth":"1","billPlanStartDate":"20171206","billStartDate":"20171006","billType":"REPAY","cardId":2,"confirmUser":"1","consumeAmt":1,"consumeNum":1,"createTime":1511427795000,"isConfirm":"N","isPlan":"N","modifyTime":1511427795000,"repayAmt":1,"repayNum":1}]
     * totalNum : 11
     * totalPage : 2
     */

    private int totalNum;
    private int totalPage;
    private String times;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * orderNo : 恒丰银行
         * date : 1
         * param : 1
         * content : 1
         */


        private String orderNo;
        private String date;
        private String cardNo;
        private String name;
        private String identityCard;
        private String mobile;
        private String beginDate;
        private String endDate;
        private String content;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
