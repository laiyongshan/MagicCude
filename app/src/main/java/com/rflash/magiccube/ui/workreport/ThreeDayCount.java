package com.rflash.magiccube.ui.workreport;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by yangfan on 2017/12/1.
 */

public class ThreeDayCount extends BaseBean {

    /**
     * result : {"revertibleInfo":{"billAmt":83000,"nonRepayAmt":3000,"nonRepayNum":1,"repayAmt":80000,"repayNum":2,"total":3},"nearly3daysInfo":[{"amt":"0","currentDate":"20171224"},{"amt":"0","currentDate":"20171223"},{"amt":"80000","currentDate":"20171225"}],"cardInfo":{"freeze":"0","serviceDue":"0","total":"9","valid":"9"},"downinfo":13,"payableInfo":{"billAmt":94600,"consumeAmt":0,"consumeNum":0,"nonConsumeAmt":94600,"nonConsumeNum":6,"total":6}}
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
         * revertibleInfo : {"billAmt":83000,"nonRepayAmt":3000,"nonRepayNum":1,"repayAmt":80000,"repayNum":2,"total":3}
         * nearly3daysInfo : [{"amt":"0","currentDate":"20171224"},{"amt":"0","currentDate":"20171223"},{"amt":"80000","currentDate":"20171225"}]
         * cardInfo : {"freeze":"0","serviceDue":"0","total":"9","valid":"9"}
         * downinfo : 13
         * payableInfo : {"billAmt":94600,"consumeAmt":0,"consumeNum":0,"nonConsumeAmt":94600,"nonConsumeNum":6,"total":6}
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
             * billAmt : 83000
             * nonRepayAmt : 3000
             * nonRepayNum : 1
             * repayAmt : 80000
             * repayNum : 2
             * total : 3
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
             * serviceDue : 0
             * total : 9
             * valid : 9
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
             * billAmt : 94600
             * consumeAmt : 0
             * consumeNum : 0
             * nonConsumeAmt : 94600
             * nonConsumeNum : 6
             * total : 6
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
             * currentDate : 20171224
             */

            private int amt;
            private int currentDate;

            public int getAmt() {
                return amt;
            }

            public void setAmt(int amt) {
                this.amt = amt;
            }

            public int getCurrentDate() {
                return currentDate;
            }

            public void setCurrentDate(int currentDate) {
                this.currentDate = currentDate;
            }
        }
    }
}
