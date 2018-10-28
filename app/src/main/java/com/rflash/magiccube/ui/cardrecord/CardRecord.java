package com.rflash.magiccube.ui.cardrecord;

/**
 * Created by yangfan on 2017/11/17.
 */

public class CardRecord {


    private String money;
    private String date;
    private String bankcard;

    public CardRecord(String money, String date, String bankcard) {
        this.money = money;
        this.date = date;
        this.bankcard = bankcard;
    }

    public CardRecord() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }
}
