package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by lenovo on 2018/11/13.
 */

public class BankInfoBean extends BaseBean {

    /**
     * result : {"bankCode":"03060000","bankName":"广发银行"}
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
         * bankCode : 03060000
         * bankName : 广发银行
         */

        private String bankCode;
        private String bankName;

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
