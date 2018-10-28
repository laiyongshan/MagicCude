package com.rflash.magiccube.ui.merchants;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/16.
 */

public class HomeCountBean extends BaseBean {
    /**
     * result : {"revertibleInfo":{"billAmt":0,"nonRepayAmt":0,"nonRepayNum":0,"repayAmt":0,"repayNum":0,"total":0},"nearly3daysInfo":[{"amt":"0","currentDate":"20181017"},{"amt":"0","currentDate":"20181016"},{"amt":"0","currentDate":"20181015"}],"cardInfo":{"freeze":"0","serviceDue":"1","total":"1","valid":"0"},"downinfo":50,"payableInfo":{"billAmt":0,"consumeAmt":0,"consumeNum":0,"nonConsumeAmt":0,"nonConsumeNum":0,"total":0}}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * revertibleInfo : {"billAmt":0,"nonRepayAmt":0,"nonRepayNum":0,"repayAmt":0,"repayNum":0,"total":0}
         * nearly3daysInfo : [{"amt":"0","currentDate":"20181017"},{"amt":"0","currentDate":"20181016"},{"amt":"0","currentDate":"20181015"}]
         * cardInfo : {"freeze":"0","serviceDue":"1","total":"1","valid":"0"}
         * downinfo : 50
         * payableInfo : {"billAmt":0,"consumeAmt":0,"consumeNum":0,"nonConsumeAmt":0,"nonConsumeNum":0,"total":0}
         */

        private RevertibleInfoBean revertibleInfo;
        private CardInfoBean cardInfo;
        private int downinfo;
        private PayableInfoBean payableInfo;
        private List<Nearly3daysInfoBean> nearly3daysInfo;

        public RevertibleInfoBean getRevertibleInfo() {
            return revertibleInfo;
        }

        public void setRevertibleInfo(RevertibleInfoBean revertibleInfo) {
            this.revertibleInfo = revertibleInfo;
        }

        public CardInfoBean getCardInfo() {
            return cardInfo;
        }

        public void setCardInfo(CardInfoBean cardInfo) {
            this.cardInfo = cardInfo;
        }

        public int getDowninfo() {
            return downinfo;
        }

        public void setDowninfo(int downinfo) {
            this.downinfo = downinfo;
        }

        public PayableInfoBean getPayableInfo() {
            return payableInfo;
        }

        public void setPayableInfo(PayableInfoBean payableInfo) {
            this.payableInfo = payableInfo;
        }

        public List<Nearly3daysInfoBean> getNearly3daysInfo() {
            return nearly3daysInfo;
        }

        public void setNearly3daysInfo(List<Nearly3daysInfoBean> nearly3daysInfo) {
            this.nearly3daysInfo = nearly3daysInfo;
        }

        public static class RevertibleInfoBean {
            /**
             * billAmt : 0
             * nonRepayAmt : 0
             * nonRepayNum : 0
             * repayAmt : 0
             * repayNum : 0
             * total : 0
             */

            private int billAmt;
            private int nonRepayAmt;
            private int nonRepayNum;
            private int repayAmt;
            private int repayNum;
            private int total;

            public int getBillAmt() {
                return billAmt;
            }

            public void setBillAmt(int billAmt) {
                this.billAmt = billAmt;
            }

            public int getNonRepayAmt() {
                return nonRepayAmt;
            }

            public void setNonRepayAmt(int nonRepayAmt) {
                this.nonRepayAmt = nonRepayAmt;
            }

            public int getNonRepayNum() {
                return nonRepayNum;
            }

            public void setNonRepayNum(int nonRepayNum) {
                this.nonRepayNum = nonRepayNum;
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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class CardInfoBean {
            /**
             * freeze : 0
             * serviceDue : 1
             * total : 1
             * valid : 0
             */

            private String freeze;
            private String serviceDue;
            private String total;
            private String valid;

            public String getFreeze() {
                return freeze;
            }

            public void setFreeze(String freeze) {
                this.freeze = freeze;
            }

            public String getServiceDue() {
                return serviceDue;
            }

            public void setServiceDue(String serviceDue) {
                this.serviceDue = serviceDue;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getValid() {
                return valid;
            }

            public void setValid(String valid) {
                this.valid = valid;
            }
        }

        public static class PayableInfoBean {
            /**
             * billAmt : 0
             * consumeAmt : 0
             * consumeNum : 0
             * nonConsumeAmt : 0
             * nonConsumeNum : 0
             * total : 0
             */

            private int billAmt;
            private int consumeAmt;
            private int consumeNum;
            private int nonConsumeAmt;
            private int nonConsumeNum;
            private int total;

            public int getBillAmt() {
                return billAmt;
            }

            public void setBillAmt(int billAmt) {
                this.billAmt = billAmt;
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

            public int getNonConsumeAmt() {
                return nonConsumeAmt;
            }

            public void setNonConsumeAmt(int nonConsumeAmt) {
                this.nonConsumeAmt = nonConsumeAmt;
            }

            public int getNonConsumeNum() {
                return nonConsumeNum;
            }

            public void setNonConsumeNum(int nonConsumeNum) {
                this.nonConsumeNum = nonConsumeNum;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class Nearly3daysInfoBean {
            /**
             * amt : 0
             * currentDate : 20181017
             */

            private String amt;
            private String currentDate;

            public String getAmt() {
                return amt;
            }

            public void setAmt(String amt) {
                this.amt = amt;
            }

            public String getCurrentDate() {
                return currentDate;
            }

            public void setCurrentDate(String currentDate) {
                this.currentDate = currentDate;
            }
        }
    }
}
