package com.rflash.magiccube.ui.merchants;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by lenovo on 2018/10/16.
 */

public class HomeRemindBean extends BaseBean {

    /**
     * result : {"TEMP_LIMIT_EXPIRES":0,"TOTAL":0,"PLAN_FAIL":0,"CARD_FEE_EXPIRES":0,"BILL_CONFIRM":0,"INSTALLMENT_EXPIRES":0,"LAST_PLAN":0,"NO_REPAY":0}
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
         * TEMP_LIMIT_EXPIRES : 0
         * TOTAL : 0
         * PLAN_FAIL : 0
         * CARD_FEE_EXPIRES : 0
         * BILL_CONFIRM : 0
         * INSTALLMENT_EXPIRES : 0
         * LAST_PLAN : 0
         * NO_REPAY : 0
         */

        private int TEMP_LIMIT_EXPIRES;
        private int TOTAL;
        private int PLAN_FAIL;
        private int CARD_FEE_EXPIRES;
        private int BILL_CONFIRM;
        private int INSTALLMENT_EXPIRES;
        private int LAST_PLAN;
        private int NO_REPAY;

        public int getTEMP_LIMIT_EXPIRES() {
            return TEMP_LIMIT_EXPIRES;
        }

        public void setTEMP_LIMIT_EXPIRES(int TEMP_LIMIT_EXPIRES) {
            this.TEMP_LIMIT_EXPIRES = TEMP_LIMIT_EXPIRES;
        }

        public int getTOTAL() {
            return TOTAL;
        }

        public void setTOTAL(int TOTAL) {
            this.TOTAL = TOTAL;
        }

        public int getPLAN_FAIL() {
            return PLAN_FAIL;
        }

        public void setPLAN_FAIL(int PLAN_FAIL) {
            this.PLAN_FAIL = PLAN_FAIL;
        }

        public int getCARD_FEE_EXPIRES() {
            return CARD_FEE_EXPIRES;
        }

        public void setCARD_FEE_EXPIRES(int CARD_FEE_EXPIRES) {
            this.CARD_FEE_EXPIRES = CARD_FEE_EXPIRES;
        }

        public int getBILL_CONFIRM() {
            return BILL_CONFIRM;
        }

        public void setBILL_CONFIRM(int BILL_CONFIRM) {
            this.BILL_CONFIRM = BILL_CONFIRM;
        }

        public int getINSTALLMENT_EXPIRES() {
            return INSTALLMENT_EXPIRES;
        }

        public void setINSTALLMENT_EXPIRES(int INSTALLMENT_EXPIRES) {
            this.INSTALLMENT_EXPIRES = INSTALLMENT_EXPIRES;
        }

        public int getLAST_PLAN() {
            return LAST_PLAN;
        }

        public void setLAST_PLAN(int LAST_PLAN) {
            this.LAST_PLAN = LAST_PLAN;
        }

        public int getNO_REPAY() {
            return NO_REPAY;
        }

        public void setNO_REPAY(int NO_REPAY) {
            this.NO_REPAY = NO_REPAY;
        }
    }
}
