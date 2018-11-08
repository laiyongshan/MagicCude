package com.rflash.magiccube.ui.addcard;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/11/3.
 */

public class AgingBean implements Serializable {
    String partTotalAmt;
    String partSeqno;
    String startTime;
    String endTime;
    String amt;
    String fee;
    String isRepaied;
    String createUser;

    public String getPartTotalAmt() {
        return partTotalAmt;
    }

    public void setPartTotalAmt(String partTotalAmt) {
        this.partTotalAmt = partTotalAmt;
    }

    public String getPartSeqno() {
        return partSeqno;
    }

    public void setPartSeqno(String partSeqno) {
        this.partSeqno = partSeqno;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getIsRepaied() {
        return isRepaied;
    }

    public void setIsRepaied(String isRepaied) {
        this.isRepaied = isRepaied;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
