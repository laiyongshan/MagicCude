package com.rflash.magiccube.ui.renewalmind;

import com.rflash.magiccube.http.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author lys
 * @time 2018/11/7 09:31
 * @desc:
 */

public class RenewalMindBean extends BaseBean {


    /**
     * result : [{"bankName":"商业银行","billDate":"22","calRepayDate":"11","cardId":4050,"cardNo":"6250808010422533","cardSeqno":"70","createTime":1543344758000,"customerName":"zeng","endTime":1543776758000,"remark":"卡片服务即将到期","remindType":"CARD_FEE_EXPIRES","repayDate":"11","serviceEndDate":"20181203","state":"REMIND"}]
     * totalNum : 1
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

    public static class ResultBean implements Serializable{
        /**
         * bankName : 商业银行
         * billDate : 22
         * calRepayDate : 11
         * cardId : 4050
         * cardNo : 6250808010422533
         * cardSeqno : 70
         * createTime : 1543344758000
         * customerName : zeng
         * endTime : 1543776758000
         * remark : 卡片服务即将到期
         * remindType : CARD_FEE_EXPIRES
         * repayDate : 11
         * serviceEndDate : 20181203
         * state : REMIND
         */

        private String bankName;
        private String billDate;
        private String calRepayDate;
        private int cardId;
        private String cardNo;
        private String cardSeqno;
        private long createTime;
        private String customerName;
        private long endTime;
        private String remark;
        private String remindType;
        private String repayDate;
        private String serviceEndDate;
        private String state;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getCalRepayDate() {
            return calRepayDate;
        }

        public void setCalRepayDate(String calRepayDate) {
            this.calRepayDate = calRepayDate;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemindType() {
            return remindType;
        }

        public void setRemindType(String remindType) {
            this.remindType = remindType;
        }

        public String getRepayDate() {
            return repayDate;
        }

        public void setRepayDate(String repayDate) {
            this.repayDate = repayDate;
        }

        public String getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(String serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
