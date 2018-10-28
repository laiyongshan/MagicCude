package com.rflash.magiccube.ui.consumemodify;

import com.rflash.magiccube.http.BaseBean;

import java.util.List;

/**
 * Created by yangfan on 2018/1/15.
 */

public class Channel extends BaseBean {


    /**
     * result : [{"channel":"30","channelName":"德颐接出渠道"},{"channel":"35","channelName":"中付接出渠道-收单"},{"channel":"38","channelName":"盛迪嘉接出"},{"channel":"40","channelName":"\t易支付收单接出"}]
     * totalNum : 4
     */

    private int totalNum;
    private List<ResultBean> result;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
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
         */

        private String channel;
        private String channelName;

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
    }
}
