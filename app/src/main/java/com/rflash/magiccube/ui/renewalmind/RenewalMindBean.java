package com.rflash.magiccube.ui.renewalmind;

import com.rflash.magiccube.http.BaseBean;

<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
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

<<<<<<< HEAD
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
=======
    private List<RenewalMindBean.ResultBean> result;

    public List<RenewalMindBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<RenewalMindBean.ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

    }

}
