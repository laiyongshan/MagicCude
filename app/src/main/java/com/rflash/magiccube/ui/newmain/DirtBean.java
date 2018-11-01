package com.rflash.magiccube.ui.newmain;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/2.
 * 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 13卡片规划状态
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 14商户类型
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 15渠道
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 16商户状态
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 17规划操作状态
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 18MCC小类
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 19消息提醒类型
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 20消息提醒状态
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 21账单类型
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 100卡类型
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1000额定规划还款时间
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1001最短交易间隔
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1002交易金额参数
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1003额定规划刷卡时间
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1004产品销售价格
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1005WEB HOST
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1006WEB IP
 11-02 00:25:06.810 4530-4530/com.rflash.magiccube I/DefaultObserver: 1007四要素验证渠道
 11-02 00:25:06.811 4530-4530/com.rflash.magiccube I/DefaultObserver: 1008渠道消费占比
 */

public class DirtBean extends BaseBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * type : {"dictTypeId":"13","dictTypeName":"卡片规划状态"}
         * option : [{"dictName":"未规划","dictId":"N"},{"dictName":"已规划","dictId":"Y"}]
         */

        private TypeBean type;
        private List<OptionBean> option;

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public List<OptionBean> getOption() {
            return option;
        }

        public void setOption(List<OptionBean> option) {
            this.option = option;
        }

        public static class TypeBean {
            /**
             * dictTypeId : 13
             * dictTypeName : 卡片规划状态
             */

            private String dictTypeId;
            private String dictTypeName;

            public String getDictTypeId() {
                return dictTypeId;
            }

            public void setDictTypeId(String dictTypeId) {
                this.dictTypeId = dictTypeId;
            }

            public String getDictTypeName() {
                return dictTypeName;
            }

            public void setDictTypeName(String dictTypeName) {
                this.dictTypeName = dictTypeName;
            }
        }

        public static class OptionBean {
            /**
             * dictName : 未规划
             * dictId : N
             */

            private String dictName;
            private String dictId;

            public String getDictName() {
                return dictName;
            }

            public void setDictName(String dictName) {
                this.dictName = dictName;
            }

            public String getDictId() {
                return dictId;
            }

            public void setDictId(String dictId) {
                this.dictId = dictId;
            }
        }
    }
}
