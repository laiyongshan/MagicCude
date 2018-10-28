package com.rflash.magiccube.ui.collection;

import com.rflash.magiccube.http.BaseBean;

/**
 * @author lys
 * @time 2018/10/8 17:10
 * @desc:
 */

public class CollectionBean extends BaseBean {

    int backgroundColor;
    String collectionStype;
    int logoId;

    public CollectionBean(int backgroundColor,String collectionStype,int logoId){
        this.backgroundColor=backgroundColor;
        this.collectionStype=collectionStype;
        this.logoId=logoId;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getCollectionStype() {
        return collectionStype;
    }

    public void setCollectionStype(String collectionStype) {
        this.collectionStype = collectionStype;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
