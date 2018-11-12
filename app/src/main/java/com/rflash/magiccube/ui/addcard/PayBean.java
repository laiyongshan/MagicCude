package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/9 14:44
 * @desc:
 */

public class PayBean extends BaseBean {

    String qrcode;
    String price;
    private List<ResultBean> result;

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * creditType : 0070
         * productName : 四要素验证
         * templateURL : http://adminweb.ehwlsj.com/meterial/pdftemplate/0070.pdf
         * times : 94
         * unitPrice : 100
         * usedTimes : 6
         */

        private String creditType;
        private String productName;
        private String templateURL;
        private int times;
        private int unitPrice;
        private int usedTimes;

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

        public String getTemplateURL() {
            return templateURL;
        }

        public void setTemplateURL(String templateURL) {
            this.templateURL = templateURL;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getUsedTimes() {
            return usedTimes;
        }

        public void setUsedTimes(int usedTimes) {
            this.usedTimes = usedTimes;
        }
    }
}
