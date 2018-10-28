package com.rflash.magiccube.ui.consumemodify;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by yangfan on 2018/1/15.
 */

public class Merchant extends BaseBean{


    /**
     * maxDownloadNum : 11
     * result : [{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5499","merchantCode":"804440180010039","merchantMccSmallClass":22,"merchantMccSmallClassName":"住宿咨询服务","merchantName":"住宿-广州市越秀区壹嘉食品商行","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5131","merchantCode":"850440151319897","merchantMccSmallClass":22,"merchantMccSmallClassName":"住宿咨询服务","merchantName":"住宿-广州三二纺织品织造有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5499","merchantCode":"850440154999895","merchantMccSmallClass":14,"merchantMccSmallClassName":"电子产品类","merchantName":"电子-广州市番禺区桥南芷菱坊茶叶店","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5691","merchantCode":"850440156919879","merchantMccSmallClass":14,"merchantMccSmallClassName":"电子产品类","merchantName":"电子-广州市海珠区步衣岛服饰店","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5732","merchantCode":"850440157329887","merchantMccSmallClass":17,"merchantMccSmallClassName":"房车类","merchantName":"房车-广州飞人电子科技有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"7399","merchantCode":"850440173999897","merchantMccSmallClass":17,"merchantMccSmallClassName":"房车类","merchantName":"房车-广州市御瑶生物科技有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5311","merchantCode":"850581053110056","merchantMccSmallClass":18,"merchantMccSmallClassName":"建材家居类","merchantName":"建材-广州市天河区员村金城百货商行","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5812","merchantCode":"850581058120122","merchantMccSmallClass":18,"merchantMccSmallClassName":"建材家居类","merchantName":"建材-广州市越秀区喜宴酒家","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"5812","merchantCode":"850581058120123","merchantMccSmallClass":19,"merchantMccSmallClassName":"批发类","merchantName":"批发-广州市番禺区斯奇酒吧","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"30","channelName":"德颐接出渠道","channelState":"valid","date":"2017-12-25","mcc":"7298","merchantCode":"850581072980052","merchantMccSmallClass":19,"merchantMccSmallClassName":"批发类","merchantName":"批发-广州市荔湾区雅致美颜坊","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"}]
     * totalNum : 11
     * totalPage : 2
     */

    private int maxDownloadNum;
    private int totalNum;
    private int totalPage;
    private List<ResultBean> result;

    public int getMaxDownloadNum() {
        return maxDownloadNum;
    }

    public void setMaxDownloadNum(int maxDownloadNum) {
        this.maxDownloadNum = maxDownloadNum;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * channel : 30
         * channelName : 德颐接出渠道
         * channelState : valid
         * date : 2017-12-25
         * mcc : 5499
         * merchantCode : 804440180010039
         * merchantMccSmallClass : 22
         * merchantMccSmallClassName : 住宿咨询服务
         * merchantName : 住宿-广州市越秀区壹嘉食品商行
         * merchantType : 1
         * merchantTypeName : 标准类
         * region : 广东省 广州市
         * state : valid
         */

        private String channel;
        private String channelName;
        private String channelState;
        private String date;
        private String mcc;
        private String merchantCode;
        private int merchantMccSmallClass;
        private String merchantMccSmallClassName;
        private String merchantName;
        private String merchantType;
        private String merchantTypeName;
        private String region;
        private String state;

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

        public String getChannelState() {
            return channelState;
        }

        public void setChannelState(String channelState) {
            this.channelState = channelState;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMcc() {
            return mcc;
        }

        public void setMcc(String mcc) {
            this.mcc = mcc;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public int getMerchantMccSmallClass() {
            return merchantMccSmallClass;
        }

        public void setMerchantMccSmallClass(int merchantMccSmallClass) {
            this.merchantMccSmallClass = merchantMccSmallClass;
        }

        public String getMerchantMccSmallClassName() {
            return merchantMccSmallClassName;
        }

        public void setMerchantMccSmallClassName(String merchantMccSmallClassName) {
            this.merchantMccSmallClassName = merchantMccSmallClassName;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantType() {
            return merchantType;
        }

        public void setMerchantType(String merchantType) {
            this.merchantType = merchantType;
        }

        public String getMerchantTypeName() {
            return merchantTypeName;
        }

        public void setMerchantTypeName(String merchantTypeName) {
            this.merchantTypeName = merchantTypeName;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
