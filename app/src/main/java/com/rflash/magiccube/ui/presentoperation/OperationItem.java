package com.rflash.magiccube.ui.presentoperation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangfan on 2017/11/27.
 */

public class OperationItem implements Parcelable{


    /**
     * accountType : T0
     * amt : 20010
     * bankName : 恒丰银行
     * cardNo : 12345
     * cardSeqno : 1
     * customerName :
     * date : 2017-11-29 00:00:00
     * merchantCode : 0987654321
     * merchantName : 商户二期pos商户
     * modifyTime : 2017-11-24 14:04:52
     * operatorTime : 2017-11-28 00:00:00
     * planId : 2017112400000008
     * planSource : INPUT
     * state : DEAL
     * tranCost : 602
     * tranType : REPAY
     * billType : 账单类型 REPAY还款账单、CARD_MANAGE空闲账单
     */

    private String accountType;
    private String amt;
    private String bankName;
    private String cardNo;
    private String cardSeqno;
    private String customerName;
    private String date;
    private String merchantCode;
    private String merchantName;
    private String modifyTime;
    private String operatorTime;
    private String planId;
    private String planSource;
    private String state;
    private String tranCost;
    private String tranType;
    private String channelName;
    private String channel;
    private String billType;
    private String merchantState;
    private String merchantBind;
    private String channelState;


    protected OperationItem(Parcel in) {
        accountType = in.readString();
        amt = in.readString();
        bankName = in.readString();
        cardNo = in.readString();
        cardSeqno = in.readString();
        customerName = in.readString();
        date = in.readString();
        merchantCode = in.readString();
        merchantName = in.readString();
        modifyTime = in.readString();
        operatorTime = in.readString();
        planId = in.readString();
        planSource = in.readString();
        state = in.readString();
        tranCost = in.readString();
        tranType = in.readString();
        channelName = in.readString();
        channel = in.readString();
        billType = in.readString();
        merchantState = in.readString();
        merchantBind = in.readString();
        channelState = in.readString();
    }

    public static final Creator<OperationItem> CREATOR = new Creator<OperationItem>() {
        @Override
        public OperationItem createFromParcel(Parcel in) {
            return new OperationItem(in);
        }

        @Override
        public OperationItem[] newArray(int size) {
            return new OperationItem[size];
        }
    };

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardSeqno() {
        return cardSeqno;
    }

    public void setCardSeqno(String cardSeqno) {
        this.cardSeqno = cardSeqno;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanSource() {
        return planSource;
    }

    public void setPlanSource(String planSource) {
        this.planSource = planSource;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTranCost() {
        return tranCost;
    }

    public void setTranCost(String tranCost) {
        this.tranCost = tranCost;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getMerchantState() {
        return merchantState;
    }

    public void setMerchantState(String merchantState) {
        this.merchantState = merchantState;
    }

    public String getMerchantBind() {
        return merchantBind;
    }

    public void setMerchantBind(String merchantBind) {
        this.merchantBind = merchantBind;
    }

    public String getChannelState() {
        return channelState;
    }

    public void setChannelState(String channelState) {
        this.channelState = channelState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accountType);
        dest.writeString(amt);
        dest.writeString(bankName);
        dest.writeString(cardNo);
        dest.writeString(cardSeqno);
        dest.writeString(customerName);
        dest.writeString(date);
        dest.writeString(merchantCode);
        dest.writeString(merchantName);
        dest.writeString(modifyTime);
        dest.writeString(operatorTime);
        dest.writeString(planId);
        dest.writeString(planSource);
        dest.writeString(state);
        dest.writeString(tranCost);
        dest.writeString(tranType);
        dest.writeString(channelName);
        dest.writeString(channel);
        dest.writeString(billType);
        dest.writeString(merchantState);
        dest.writeString(merchantBind);
        dest.writeString(channelState);
    }
}
