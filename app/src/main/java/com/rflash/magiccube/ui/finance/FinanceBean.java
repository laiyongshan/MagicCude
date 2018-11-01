package com.rflash.magiccube.ui.finance;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/9.
 */

public class FinanceBean extends BaseBean {

    /**
     * result : [{"bankName":"商业银行","cardNo":"6250808010422533","cardSeqno":"70","customerName":"zeng","repayAmt":43600,"repayNum":2,"reportDate":"201810","revenue":12969,"saleAmt":21800,"saleNum":1,"serviceAmt":2200000,"serviceFee":13200,"serviceRate":"0.006","serviceType":"FIXED_LIMIT","tranCost":231},{"bankName":"平安银行","cardNo":"5268550416353118","cardSeqno":"69","customerName":"zeng","repayAmt":258000,"repayNum":2,"reportDate":"201810","revenue":11222,"saleAmt":543600,"saleNum":7,"serviceAmt":2500000,"serviceFee":15000,"serviceRate":"0.006","serviceType":"FIXED_LIMIT","tranCost":3778},{"bankName":"招商银行","cardNo":"4392268333819671","cardSeqno":"68","customerName":"zeng","repayAmt":564319,"repayNum":3,"reportDate":"201810","revenue":12486,"saleAmt":85400,"saleNum":2,"serviceAmt":2200000,"serviceFee":13200,"serviceRate":"0.006","serviceType":"FIXED_LIMIT","tranCost":714},{"bankName":"华夏银行","cardNo":"6259691131986953","cardSeqno":"65","customerName":"zeng","repayAmt":2000,"repayNum":1,"reportDate":"201810","revenue":279,"saleAmt":3500,"saleNum":2,"serviceAmt":100000,"serviceFee":600,"serviceRate":"0.006","serviceType":"FIXED_LIMIT","tranCost":321},{"bankName":"招商银行","cardNo":"4392268333819671","cardSeqno":"68","customerName":"zeng","repayAmt":705738,"repayNum":5,"reportDate":"201809","revenue":8179,"saleAmt":797452,"saleNum":7,"serviceAmt":2200000,"serviceFee":13200,"serviceRate":"0.006","serviceType":"FIXED_LIMIT","tranCost":5021}]
     * revenue : 45135
     * saleAmt : 1451752
     * serviceFee : 55200
     * totalNum : 5
     * totalPage : 1
     * tranCost : 10065
     */

    private int revenue;
    private int saleAmt;
    private int serviceFee;
    private int totalNum;
    private int totalPage;
    private int tranCost;
    private List<ResultBean> result;

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(int saleAmt) {
        this.saleAmt = saleAmt;
    }

    public int getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(int serviceFee) {
        this.serviceFee = serviceFee;
    }

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

    public int getTranCost() {
        return tranCost;
    }

    public void setTranCost(int tranCost) {
        this.tranCost = tranCost;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean extends BaseBean{
        /**
         * bankName : 商业银行
         * cardNo : 6250808010422533
         * cardSeqno : 70
         * customerName : zeng
         * repayAmt : 43600
         * repayNum : 2
         * reportDate : 201810
         * revenue : 12969
         * saleAmt : 21800
         * saleNum : 1
         * serviceAmt : 2200000
         * serviceFee : 13200
         * serviceRate : 0.006
         * serviceType : FIXED_LIMIT
         * tranCost : 231
         */

        private String bankName;
        private String cardNo;
        private String cardSeqno;
        private String customerName;
        private int repayAmt;
        private int repayNum;
        private String reportDate;
        private int revenue;
        private int saleAmt;
        private int saleNum;
        private int serviceAmt;
        private int serviceFee;
        private String serviceRate;
        private String serviceType;
        private int tranCost;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
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

        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        public int getRevenue() {
            return revenue;
        }

        public void setRevenue(int revenue) {
            this.revenue = revenue;
        }

        public int getSaleAmt() {
            return saleAmt;
        }

        public void setSaleAmt(int saleAmt) {
            this.saleAmt = saleAmt;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getServiceAmt() {
            return serviceAmt;
        }

        public void setServiceAmt(int serviceAmt) {
            this.serviceAmt = serviceAmt;
        }

        public int getServiceFee() {
            return serviceFee;
        }

        public void setServiceFee(int serviceFee) {
            this.serviceFee = serviceFee;
        }

        public String getServiceRate() {
            return serviceRate;
        }

        public void setServiceRate(String serviceRate) {
            this.serviceRate = serviceRate;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public int getTranCost() {
            return tranCost;
        }

        public void setTranCost(int tranCost) {
            this.tranCost = tranCost;
        }
    }
}
