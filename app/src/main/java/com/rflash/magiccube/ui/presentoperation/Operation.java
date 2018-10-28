package com.rflash.magiccube.ui.presentoperation;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.ui.presentoperation.OperationItem;

import java.util.List;

/**
 * Created by yangfan on 2017/11/27.
 */

public class Operation extends BaseBean {


    /**
     * result : []
     * totalNum : 0
     * totalPage : 0
     */

    private int totalNum;
    private int totalPage;

    private List<OperationItem> result;

    public List<OperationItem> getResult() {
        return result;
    }

    public void setResult(List<OperationItem> result) {
        this.result = result;
    }

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
}
