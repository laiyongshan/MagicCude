package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by lenovo on 2018/11/3.
 */

public class VerfyBean extends BaseBean {

    /**
     * times : 99
     * validateStatus : 01
     */

    private int times;
    private String validateStatus;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(String validateStatus) {
        this.validateStatus = validateStatus;
    }
}
