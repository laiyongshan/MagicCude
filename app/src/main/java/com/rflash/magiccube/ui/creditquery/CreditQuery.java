package com.rflash.magiccube.ui.creditquery;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by guobaihui on 2018/03/15.
 */

public class CreditQuery extends BaseBean {

    /**
     * {"result":[{"creditType":"0032","productName":"用户画像","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0032.pdf","times":0,"unitPrice":240,"usedTimes":0},{"creditType":"0046","productName":"疑似套现","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0046.pdf","times":0,"unitPrice":5589,"usedTimes":0},{"creditType":"0058","productName":"用户账单真伪核验","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0058.pdf","times":0,"unitPrice":11100,"usedTimes":0},{"creditType":"0070","productName":"四要素验证","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0070.pdf","times":1,"unitPrice":1256,"usedTimes":2},{"creditType":"0071","productName":"银联用户交易报告","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0071.pdf","times":0,"unitPrice":100,"usedTimes":0},{"creditType":"0072","productName":"运营商三要素认证","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0072.pdf","times":0,"unitPrice":200,"usedTimes":0},{"creditType":"0116","productName":"云信贷申请反欺诈","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0116.pdf","times":0,"unitPrice":100,"usedTimes":0},{"creditType":"0147","productName":"云信贷互联反欺诈","templateURL":"http://10.252.1.170:8085/meterial/pdftemplate/0147.pdf","times":0,"unitPrice":100,"usedTimes":0}]}
     */


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * creditType : 0032
         * productName : 用户画像
         * unitPrice : 0
         * times : 100
         * usedTimes : 20171206
         * templateURL : http://10.252.1.170:8085/meterial/pdftemplate/0032.pdf
         */

        private String creditType;
        private String productName;
        private String unitPrice;
        private String times;
        private String usedTimes;
        private String templateURL;

        public String getCreditType() {
            return creditType;
        }

        public void setCreditType(String creditType) {
            this.creditType = creditType;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getUsedTimes() {
            return usedTimes;
        }

        public void setUsedTimes(String usedTimes) {
            this.usedTimes = usedTimes;
        }

        public String getTemplateURL() {
            return templateURL;
        }

        public void setTemplateURL(String templateURL) {
            this.templateURL = templateURL;
        }
    }
}
