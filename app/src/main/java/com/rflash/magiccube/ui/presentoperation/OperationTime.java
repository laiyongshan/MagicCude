package com.rflash.magiccube.ui.presentoperation;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by yangfan on 2017/12/26.
 */

public class OperationTime extends BaseBean{

    private String timeCheck;
    private String lastOperatorTime;
    private String minTranInterval;
    private String verifyCheck;// 是否通过验证

    public String getMinTranInterval() {
        return minTranInterval;
    }

    public void setMinTranInterval(String minTranInterval) {
        this.minTranInterval = minTranInterval;
    }

    public String getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(String timeCheck) {
        this.timeCheck = timeCheck;
    }

    public String getLastOperatorTime() {
        return lastOperatorTime;
    }

    public void setLastOperatorTime(String lastOperatorTime) {
        this.lastOperatorTime = lastOperatorTime;
    }

    public String getVerifyCheck() {
        return verifyCheck;
    }

    public void setVerifyCheck(String verifyCheck) {
        this.verifyCheck = verifyCheck;
    }
}
