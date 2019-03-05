package com.rflash.magiccube.ui.notify;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/12 09:47
 * @desc:
 */

public class NotifyBean extends BaseBean {
    private int totalNum;
    private int totalPage;
    private List<ResultBean> result;

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public static class ResultBean {
        String content;
        String title;
        String date;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }
    }
}
