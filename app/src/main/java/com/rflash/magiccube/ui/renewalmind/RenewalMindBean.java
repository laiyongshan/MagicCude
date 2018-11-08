package com.rflash.magiccube.ui.renewalmind;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/7 09:31
 * @desc:
 */

public class RenewalMindBean extends BaseBean {

    private int totalNum;
    private int totalPage;

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

    private List<RenewalMindBean.ResultBean> result;

    public List<RenewalMindBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<RenewalMindBean.ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

    }

}
