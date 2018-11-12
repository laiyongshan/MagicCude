package com.rflash.magiccube.ui.addcard;

import com.rflash.magiccube.http.BaseBean;

/**
 * @author lys
 * @time 2018/11/9 15:31
 * @desc:
 */

public class ProductDetailBean extends BaseBean {

    /**
     * result : [{"cardNo":"6214832028356553","content":"{\"paySerialNo\":\"501811041044477005193100059855\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"01\",\"cardType\":\"1\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"招商银行\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110450936427\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 10:44:48","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000549"},{"cardNo":"6214832028356553","content":"{\"paySerialNo\":\"501811041046045636734500060245\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"01\",\"cardType\":\"1\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"招商银行\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110476421798\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 10:46:04","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000550"},{"cardNo":"6214832028356553","content":"{\"paySerialNo\":\"501811041058242505614900063988\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"01\",\"cardType\":\"1\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"招商银行\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110492209599\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 10:58:24","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000551"},{"cardNo":"6214832028356553","content":"{\"paySerialNo\":\"501811041531301065135300152291\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"01\",\"cardType\":\"1\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"招商银行\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110461787970\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 15:31:30","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000552"},{"cardNo":"621483202835","content":"{\"paySerialNo\":\"501811041544576939842900156160\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"02\",\"cardType\":\"0\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"0\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110464492213\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 15:44:57","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000553"},{"cardNo":"6214832028356553","content":"{\"paySerialNo\":\"501811041545206438656400156263\",\"rspMsg\":\"交易成功\",\"resv\":\"\",\"validateStatus\":\"01\",\"cardType\":\"1\",\"rspCod\":\"000000\",\"remark\":\"\",\"bankName\":\"招商银行\",\"execType\":\"S\",\"cooperSerialNo\":\"2018110491957823\",\"oriTransDate\":\"20181104\"}","creditType":"0070","date":"2018-11-04 15:45:20","identityCard":"440881199308146116","mobile":"15088132079","name":"赖永善","orderNo":"2018110400000554"}]
     * times : 94
     * totalNum : 6
     * totalPage : 1
     */

    private int times;
    private int totalNum;
    private int totalPage;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
