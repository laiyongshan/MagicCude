package com.rflash.magiccube.ui.credithandle;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.ui.bill.Bill;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mango on 2018/3/26.
 */

public class CardLink  extends BaseBean implements Serializable{

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        private String name;
        private String link;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
