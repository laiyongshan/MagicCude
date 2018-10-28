package com.rflash.magiccube.ui.other;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by lenovo on 2018/10/7.
 */

public class OtherBean extends BaseBean {
    private String name;
    private int imgId;

    public OtherBean(String name, int imgId){
        this.name=name;
        this.imgId=imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
