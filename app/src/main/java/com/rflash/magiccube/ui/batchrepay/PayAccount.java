package com.rflash.magiccube.ui.batchrepay;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by yangfan on 2017/11/28.
 */

public class PayAccount extends BaseBean{

    private String payAccount;
    private String balance;

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
