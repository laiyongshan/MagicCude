package com.rflash.magiccube.http;

import com.rflash.magiccube.Config;

import java.io.Serializable;

/**
 * Created by yangfan on 2017/11/27.
 *
 * 所有的data  base64转码 后 得到json串转成的bean  必须继承BaseBean
 */

public class BaseBean implements Serializable{


    private String version;
    private String requestNo;
    private String machineCode;
    private String account;
    private String respCode;
    private String respDesc;
    private String signature;
    private String data;


    public BaseBean() {
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return respCode.equals(Config.HTTP_SUCCESS);
    }
}
