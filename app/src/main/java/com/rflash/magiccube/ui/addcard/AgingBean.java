package com.rflash.magiccube.ui.addcard;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/11/3.
 */

public class AgingBean implements Serializable {
//    String partTotalAmt;
//    String partSeqno;
//    String startTime;
//    String endTime;
//    String amt;
//    String fee;
//    String isRepaied;
//    String createUser;
//    private long createTime;
//    private long modifyTime;

    private int amt;
    private long createTime;
    private String createUser;
    private String endTime;
    private int fee;
    private String isRepaied;
    private long modifyTime;
    private int partSeqno;
    private int partTotalAmt;
    private String startTime;

//    public void setModifyTime(long modifyTime) {
//        this.modifyTime = modifyTime;
//    }
//
//    public long getModifyTime() {
//        return modifyTime;
//    }
//
//    public void setCreateTime(long createTime) {
//        this.createTime = createTime;
//    }
//
//    public long getCreateTime() {
//        return createTime;
//    }
//
//    public String getPartTotalAmt() {
//        return partTotalAmt;
//    }
//
//    public void setPartTotalAmt(String partTotalAmt) {
//        this.partTotalAmt = partTotalAmt;
//    }
//
//    public String getPartSeqno() {
//        return partSeqno;
//    }
//
//    public void setPartSeqno(String partSeqno) {
//        this.partSeqno = partSeqno;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getAmt() {
//        return amt;
//    }
//
//    public void setAmt(String amt) {
//        this.amt = amt;
//    }
//
//    public String getFee() {
//        return fee;
//    }
//
//    public void setFee(String fee) {
//        this.fee = fee;
//    }
//
//    public String getIsRepaied() {
//        return isRepaied;
//    }
//
//    public void setIsRepaied(String isRepaied) {
//        this.isRepaied = isRepaied;
//    }
//
//    public String getCreateUser() {
//        return createUser;
//    }
//
//    public void setCreateUser(String createUser) {
//        this.createUser = createUser;
//    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getIsRepaied() {
        return isRepaied;
    }

    public void setIsRepaied(String isRepaied) {
        this.isRepaied = isRepaied;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getPartSeqno() {
        return partSeqno;
    }

    public void setPartSeqno(int partSeqno) {
        this.partSeqno = partSeqno;
    }

    public int getPartTotalAmt() {
        return partTotalAmt;
    }

    public void setPartTotalAmt(int partTotalAmt) {
        this.partTotalAmt = partTotalAmt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
