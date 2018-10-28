package com.rflash.magiccube.ui.creditrecharge;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by Mango on 2018/3/19.
 */

public class CreditRechare extends BaseBean {
    String qrcode;
    String price;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
