package com.rflash.magiccube.ui.shanghu.download;

import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.ui.shanghu.ShanghuBean;

import java.util.List;

/**
 * Created by lenovo on 2018/10/11.
 */

public class DownloadBean extends BaseBean {

    /**
     * maxDownloadNum : 200
     * result : [{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5999","merchantCode":"893982159990421","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"广州市越秀区彦瑶包装材料商行","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"7399","merchantCode":"893976473991213","merchantMccSmallClass":14,"merchantMccSmallClassName":"电子产品类","merchantName":"广州众汇电子科技有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"7297","merchantCode":"893946472976677","merchantMccSmallClass":21,"merchantMccSmallClassName":"娱乐餐饮珠宝","merchantName":"广州市增城宝之岛沐足休闲中心（普通合伙）","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5065","merchantCode":"893946050657421","merchantMccSmallClass":19,"merchantMccSmallClassName":"批发类","merchantName":"广州天河新民基电器厂","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5411","merchantCode":"893880654110104","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"华润万家生活超市（广州）有限公司龙洞瑜翠园店","merchantType":"2","merchantTypeName":"优惠类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5651","merchantCode":"893860256519979","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"广州市越秀区杰斌时装店","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5311","merchantCode":"893737453114818","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"广州正聚百货有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5511","merchantCode":"893732555110701","merchantMccSmallClass":17,"merchantMccSmallClassName":"房车类","merchantName":"广州中粤期舰汽车销售有限公司","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"5411","merchantCode":"893635154110098","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"华润万家生活超市（广州）有限公司夏园店","merchantType":"2","merchantTypeName":"优惠类","region":"广东省 广州市","state":"valid"},{"channel":"38","channelName":"盛01","channelState":"valid","date":"2018-08-21","mcc":"8299","merchantCode":"893605582999518","merchantMccSmallClass":20,"merchantMccSmallClassName":"日用百货超市类","merchantName":"广州市增城和顺机动车驾驶员培训中心","merchantType":"1","merchantTypeName":"标准类","region":"广东省 广州市","state":"valid"}]
     * totalNum : 50
     * totalPage : 5
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
         * channel : 38
         * channelName : 盛01
         * channelState : valid
         * date : 2018-08-21
         * mcc : 5999
         * merchantCode : 893982159990421
         * merchantMccSmallClass : 20
         * merchantMccSmallClassName : 日用百货超市类
         * merchantName : 广州市越秀区彦瑶包装材料商行
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
        private boolean selected;

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean getSelected(){
            return  selected;
        }

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
