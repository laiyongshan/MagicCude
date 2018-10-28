package com.rflash.magiccube.ui.salesmen;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * @author lys
 * @time 2018/10/11 09:34
 * @desc:
 */

public class SalesmenBean extends BaseBean {
    /**
     * result : [{"createTime":1538135317000,"createUser":"ceshi","id":100,"modifyTime":1538135317000,"modifyUser":"ceshi","name":"磁轭","pointId":154,"profitRatio":"0.1"}]
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
         * createTime : 1538135317000
         * createUser : ceshi
         * id : 100
         * modifyTime : 1538135317000
         * modifyUser : ceshi
         * name : 磁轭
         * pointId : 154
         * profitRatio : 0.1
         */

        private long createTime;
        private String createUser;
        private int id;
        private long modifyTime;
        private String modifyUser;
        private String name;
        private int pointId;
        private String profitRatio;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPointId() {
            return pointId;
        }

        public void setPointId(int pointId) {
            this.pointId = pointId;
        }

        public String getProfitRatio() {
            return profitRatio;
        }

        public void setProfitRatio(String profitRatio) {
            this.profitRatio = profitRatio;
        }
    }
}
