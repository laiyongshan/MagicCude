package com.rflash.magiccube.ui.operationtoday.unoperation;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by yangfan on 2017/11/28.
 */
@Deprecated
public class Confirm extends BaseBean{


    /**
     * account : testcard
     * machineCode : P2L0100000074016032
     * requestNo : 20171128145745
     * respCode : 0000
     * respDesc : 成功
     * signature : TNixySU3vsroiiSpR5Bm/Ro6gY+zcKmB6Y6tSE6a7lMlEev5JFWGhH8zaJlw136sFW/DbqJckVVH/+/aKIWlxnlMnA0GxdMLM6d6hGaZvmjBaQLWitcy7WdRAzrphHZgjLpO2plP8cmIs4y/GPt2GJcj4/9g2G4+bcRNjPEzTGE=
     * version : V1.0
     */

    private String account;
    private String machineCode;
    private String requestNo;
    private String respCode;
    private String respDesc;
    private String signature;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
