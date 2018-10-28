package com.rflash.magiccube.ui.creditqueryitem;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by Mango on 2018/3/19.
 */

public class CreditQueryItem extends BaseBean {
    private String times;
    private String orderNo;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
