package com.rflash.magiccube.ui.cardmanager.increase;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 */

public class IncreaseBean  extends BaseBean {
    /**
     * result : [{"amt":10000,"cardId":1996,"changeTime":1540524232000,"changeTimeStr":"2018-10-26","changeType":"LIMIT_UP","createTime":1540610795000,"createUser":"ceshi","newLimit":4490000,"originalLimit":4480000}]
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

    public static class ResultBean {
        /**
         * amt : 10000
         * cardId : 1996
         * changeTime : 1540524232000
         * changeTimeStr : 2018-10-26
         * changeType : LIMIT_UP
         * createTime : 1540610795000
         * createUser : ceshi
         * newLimit : 4490000
         * originalLimit : 4480000
         */

        private int amt;
        private int cardId;
        private long changeTime;
        private String changeTimeStr;
        private String changeType;
        private long createTime;
        private String createUser;
        private int newLimit;
        private int originalLimit;

        public int getAmt() {
            return amt;
        }

        public void setAmt(int amt) {
            this.amt = amt;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public long getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(long changeTime) {
            this.changeTime = changeTime;
        }

        public String getChangeTimeStr() {
            return changeTimeStr;
        }

        public void setChangeTimeStr(String changeTimeStr) {
            this.changeTimeStr = changeTimeStr;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
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

        public int getNewLimit() {
            return newLimit;
        }

        public void setNewLimit(int newLimit) {
            this.newLimit = newLimit;
        }

        public int getOriginalLimit() {
            return originalLimit;
        }

        public void setOriginalLimit(int originalLimit) {
            this.originalLimit = originalLimit;
        }
    }
}
