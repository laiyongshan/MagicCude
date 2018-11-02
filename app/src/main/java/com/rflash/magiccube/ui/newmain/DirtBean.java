package com.rflash.magiccube.ui.newmain;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/2.
 *
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
