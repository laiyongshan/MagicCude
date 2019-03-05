package com.rflash.magiccube.ui.cardmanager.cardbase;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/27.
 */

public class BaseInfoBean extends BaseBean {

    /**
     * result : {"availableAmt":23563,"bankCode":"03041100","billDate":"23","cardBankName":"华夏银行","cardId":4194,"cardMedia":"IC_CARD","cardNo":"6259691131986193","cardSeqno":"74","createTime":1540346296000,"createUser":"zengyan","currentRepayAmt":25689,"customerID":"200104166204060512","customerName":"zeng","fixedLimit":100003,"freePlanRate":"0","initAmt":25588,"installmentList":[{"amt":2000000,"createTime":1544111294000,"createUser":"zengyan","endTime":"20190104","fee":2000,"isRepaied":"N","modifyTime":1544111294000,"partSeqno":2,"partTotalAmt":2000000,"startTime":"20031206"}],"isFreePlan":"N","isHaveKey":"N","isHolidayPlan":"Y","lastPlanTime":1543235734000,"loginPasswd":"9f47a81f8fa4790fa3400c357d399e6a","loginUsername":"9f47a81f8fa4790fa3400c357d399e6a","modifyTime":1544111294000,"modifyUser":"zengyan","paidAmt":10000,"phone":"16200247891","planState":"SUCCESS","pointId":88,"queryPasswd":"9f47a81f8fa4790fa3400c357d399e6a","repayDate":"10","repayDateType":"FIXED","salesMan":108,"serviceAmt":100000,"serviceEndDate":"20181231","serviceRate":"60.0","serviceStartDate":"20181024","serviceState":"unexpired","serviceType":"FIXED_LIMIT","state":"VALID","tempLimit":0,"tranPasswd":"9f47a81f8fa4790fa3400c357d399e6a","verifyState":"N","verifyType":"VERIFY2"}
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
         * availableAmt : 23563
         * bankCode : 03041100
         * billDate : 23
         * cardBankName : 华夏银行
         * cardId : 4194
         * cardMedia : IC_CARD
         * cardNo : 6259691131986193
         * cardSeqno : 74
         * createTime : 1540346296000
         * createUser : zengyan
         * currentRepayAmt : 25689
         * customerID : 200104166204060512
         * customerName : zeng
         * fixedLimit : 100003
         * freePlanRate : 0
         * initAmt : 25588
         * installmentList : [{"amt":2000000,"createTime":1544111294000,"createUser":"zengyan","endTime":"20190104","fee":2000,"isRepaied":"N","modifyTime":1544111294000,"partSeqno":2,"partTotalAmt":2000000,"startTime":"20031206"}]
         * isFreePlan : N
         * isHaveKey : N
         * isHolidayPlan : Y
         * lastPlanTime : 1543235734000
         * loginPasswd : 9f47a81f8fa4790fa3400c357d399e6a
         * loginUsername : 9f47a81f8fa4790fa3400c357d399e6a
         * modifyTime : 1544111294000
         * modifyUser : zengyan
         * paidAmt : 10000
         * phone : 16200247891
         * planState : SUCCESS
         * pointId : 88
         * queryPasswd : 9f47a81f8fa4790fa3400c357d399e6a
         * repayDate : 10
         * repayDateType : FIXED
         * salesMan : 108
         * serviceAmt : 100000
         * serviceEndDate : 20181231
         * serviceRate : 60.0
         * serviceStartDate : 20181024
         * serviceState : unexpired
         * serviceType : FIXED_LIMIT
         * state : VALID
         * tempLimit : 0
         * tranPasswd : 9f47a81f8fa4790fa3400c357d399e6a
         * verifyState : N
         * verifyType : VERIFY2
         */

        private int availableAmt;
        private String bankCode;
        private String billDate;
        private String cardBankName;
        private int cardId;
        private String cardMedia;
        private String cardNo;
        private String cardSeqno;
        private long createTime;
        private String createUser;
        private int currentRepayAmt;
        private String customerID;
        private String customerName;
        private int fixedLimit;
        private String freePlanRate;
        private int initAmt;
        private String isFreePlan;
        private String isHaveKey;
        private String isHolidayPlan;
        private long lastPlanTime;
        private String loginPasswd;
        private String loginUsername;
        private long modifyTime;
        private String modifyUser;
        private int paidAmt;
        private String phone;
        private String planState;
        private int pointId;
        private String queryPasswd;
        private String repayDate;
        private String repayDateType;
        private int salesMan;
        private int serviceAmt;
        private String serviceEndDate;
        private String serviceRate;
        private String serviceStartDate;
        private String serviceState;
        private String serviceType;
        private String state;
        private int tempLimit;
        private String tranPasswd;
        private String verifyState;
        private String verifyType;
        private List<InstallmentListBean> installmentList;

        public int getAvailableAmt() {
            return availableAmt;
        }

        public void setAvailableAmt(int availableAmt) {
            this.availableAmt = availableAmt;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getCardBankName() {
            return cardBankName;
        }

        public void setCardBankName(String cardBankName) {
            this.cardBankName = cardBankName;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public String getCardMedia() {
            return cardMedia;
        }

        public void setCardMedia(String cardMedia) {
            this.cardMedia = cardMedia;
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

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public int getCurrentRepayAmt() {
            return currentRepayAmt;
        }

        public void setCurrentRepayAmt(int currentRepayAmt) {
            this.currentRepayAmt = currentRepayAmt;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getFixedLimit() {
            return fixedLimit;
        }

        public void setFixedLimit(int fixedLimit) {
            this.fixedLimit = fixedLimit;
        }

        public String getFreePlanRate() {
            return freePlanRate;
        }

        public void setFreePlanRate(String freePlanRate) {
            this.freePlanRate = freePlanRate;
        }

        public int getInitAmt() {
            return initAmt;
        }

        public void setInitAmt(int initAmt) {
            this.initAmt = initAmt;
        }

        public String getIsFreePlan() {
            return isFreePlan;
        }

        public void setIsFreePlan(String isFreePlan) {
            this.isFreePlan = isFreePlan;
        }

        public String getIsHaveKey() {
            return isHaveKey;
        }

        public void setIsHaveKey(String isHaveKey) {
            this.isHaveKey = isHaveKey;
        }

        public String getIsHolidayPlan() {
            return isHolidayPlan;
        }

        public void setIsHolidayPlan(String isHolidayPlan) {
            this.isHolidayPlan = isHolidayPlan;
        }

        public long getLastPlanTime() {
            return lastPlanTime;
        }

        public void setLastPlanTime(long lastPlanTime) {
            this.lastPlanTime = lastPlanTime;
        }

        public String getLoginPasswd() {
            return loginPasswd;
        }

        public void setLoginPasswd(String loginPasswd) {
            this.loginPasswd = loginPasswd;
        }

        public String getLoginUsername() {
            return loginUsername;
        }

        public void setLoginUsername(String loginUsername) {
            this.loginUsername = loginUsername;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getModifyUser() {
            return modifyUser;
        }

        public void setModifyUser(String modifyUser) {
            this.modifyUser = modifyUser;
        }

        public int getPaidAmt() {
            return paidAmt;
        }

        public void setPaidAmt(int paidAmt) {
            this.paidAmt = paidAmt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPlanState() {
            return planState;
        }

        public void setPlanState(String planState) {
            this.planState = planState;
        }

        public int getPointId() {
            return pointId;
        }

        public void setPointId(int pointId) {
            this.pointId = pointId;
        }

        public String getQueryPasswd() {
            return queryPasswd;
        }

        public void setQueryPasswd(String queryPasswd) {
            this.queryPasswd = queryPasswd;
        }

        public String getRepayDate() {
            return repayDate;
        }

        public void setRepayDate(String repayDate) {
            this.repayDate = repayDate;
        }

        public String getRepayDateType() {
            return repayDateType;
        }

        public void setRepayDateType(String repayDateType) {
            this.repayDateType = repayDateType;
        }

        public int getSalesMan() {
            return salesMan;
        }

        public void setSalesMan(int salesMan) {
            this.salesMan = salesMan;
        }

        public int getServiceAmt() {
            return serviceAmt;
        }

        public void setServiceAmt(int serviceAmt) {
            this.serviceAmt = serviceAmt;
        }

        public String getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(String serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public String getServiceRate() {
            return serviceRate;
        }

        public void setServiceRate(String serviceRate) {
            this.serviceRate = serviceRate;
        }

        public String getServiceStartDate() {
            return serviceStartDate;
        }

        public void setServiceStartDate(String serviceStartDate) {
            this.serviceStartDate = serviceStartDate;
        }

        public String getServiceState() {
            return serviceState;
        }

        public void setServiceState(String serviceState) {
            this.serviceState = serviceState;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getTempLimit() {
            return tempLimit;
        }

        public void setTempLimit(int tempLimit) {
            this.tempLimit = tempLimit;
        }

        public String getTranPasswd() {
            return tranPasswd;
        }

        public void setTranPasswd(String tranPasswd) {
            this.tranPasswd = tranPasswd;
        }

        public String getVerifyState() {
            return verifyState;
        }

        public void setVerifyState(String verifyState) {
            this.verifyState = verifyState;
        }

        public String getVerifyType() {
            return verifyType;
        }

        public void setVerifyType(String verifyType) {
            this.verifyType = verifyType;
        }

        public List<InstallmentListBean> getInstallmentList() {
            return installmentList;
        }

        public void setInstallmentList(List<InstallmentListBean> installmentList) {
            this.installmentList = installmentList;
        }

        public static class InstallmentListBean {
            /**
             * amt : 2000000
             * createTime : 1544111294000
             * createUser : zengyan
             * endTime : 20190104
             * fee : 2000
             * isRepaied : N
             * modifyTime : 1544111294000
             * partSeqno : 2
             * partTotalAmt : 2000000
             * startTime : 20031206
             */

            private int amt;
            private long createTime;
            private String createUser;
            private String endTime;
            private int fee;
            private String isRepaied;
            private long modifyTime;
            private int partSeqno;
            private int partTotalAmt;
            private String startTime;

            public int getAmt() {
                return amt;
            }

            public void setAmt(int amt) {
                this.amt = amt;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public String getIsRepaied() {
                return isRepaied;
            }

            public void setIsRepaied(String isRepaied) {
                this.isRepaied = isRepaied;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getPartSeqno() {
                return partSeqno;
            }

            public void setPartSeqno(int partSeqno) {
                this.partSeqno = partSeqno;
            }

            public int getPartTotalAmt() {
                return partTotalAmt;
            }

            public void setPartTotalAmt(int partTotalAmt) {
                this.partTotalAmt = partTotalAmt;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }
    }
}
