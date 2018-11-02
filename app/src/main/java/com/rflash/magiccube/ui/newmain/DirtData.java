package com.rflash.magiccube.ui.newmain;

import android.content.Context;

import com.rflash.magiccube.util.CatchManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lys
 * @time 2018/11/2 15:30
 * @desc: 13卡片规划状态
 * 14商户类型
 * 15渠道
 * 16商户状态
 * 17规划操作状态
 * 18MCC小类
 * 19消息提醒类型
 * 20消息提醒状态
 * 21账单类型
 * 100卡类型
 * 1000额定规划还款时间
 * 1001最短交易间隔
 * 1002交易金额参数
 * 1003额定规划刷卡时间
 * 1004产品销售价格
 * 1005WEB HOST
 * 1006WEB IP
 * 1007四要素验证渠道
 * 1008渠道消费占比
 */

public class DirtData {
    public final static String key = "Dirt";
    Context context;

    public DirtData(Context context) {
        this.context = context;
    }

    public String[] tranTypeArr = {"消费", "还款"};//交易类型
    public String[] tranTypeOptions={"SALE","REPAY"};

    public String[] isNeedT0Arr={"是","否"};//是否需要T0
    public String[] isNeedOptions={"Y","N"};

    public List<String> planStateList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> planStateOptions = new ArrayList<>();

    public List<String> channelList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> channelOptions = new ArrayList<>();

    public List<String> merchantsTypeList = new ArrayList<>();//商户类型
    public List<DirtBean.ResultBean.OptionBean> merchantsTypeOptions = new ArrayList<>();


    List<DirtBean.ResultBean> list = (List<DirtBean.ResultBean>) CatchManager.getCatchData(context, key);

    //卡片规划状态
    public List<DirtBean.ResultBean.OptionBean> cardPlanState() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("13"))
                return resultBean.getOption();
        }
        return null;
    }


    //商户类型
    public List<DirtBean.ResultBean.OptionBean> merchantsType() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("14"))
                merchantsTypeOptions = resultBean.getOption();
        }
        return merchantsTypeOptions;
    }

    public List<String> getMerchantsTypeList() {
        if (channnel() != null) {
            for (DirtBean.ResultBean.OptionBean optionBean : merchantsTypeOptions) {
                merchantsTypeList.add(optionBean.getDictName());
            }
        }
        return merchantsTypeList;
    }


    //渠道
    public List<DirtBean.ResultBean.OptionBean> channnel() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("15"))
                channelOptions = resultBean.getOption();
        }
        return channelOptions;
    }

    public List<String> getChannelList() {
        if (channnel() != null) {
            for (DirtBean.ResultBean.OptionBean optionBean : channelOptions) {
                channelList.add(optionBean.getDictName());
            }
        }
        return channelList;
    }


    //商户状态
    public List<DirtBean.ResultBean.OptionBean> merchantsState() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("16"))
                return resultBean.getOption();
        }
        return null;
    }

    //17规划操作状态
    public List<DirtBean.ResultBean.OptionBean> planState() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("17")) {
                planStateOptions = resultBean.getOption();
            }
        }
        return planStateOptions;
    }

    public List<String> getPlanStateList() {
        if (planState() != null) {
            for (DirtBean.ResultBean.OptionBean optionBean : planStateOptions) {
                planStateList.add(optionBean.getDictName());
            }
        }
        return planStateList;
    }


    //18MCC小类
    public List<DirtBean.ResultBean.OptionBean> mcc() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("18"))
                return resultBean.getOption();
        }
        return null;
    }


    //21账单类型
    public List<DirtBean.ResultBean.OptionBean> billType() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("21"))
                return resultBean.getOption();
        }
        return null;
    }

    //100卡类型
    public List<DirtBean.ResultBean.OptionBean> cardType() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("100"))
                return resultBean.getOption();
        }
        return null;
    }

    //1002交易金额参数
    public List<DirtBean.ResultBean.OptionBean> amtParams() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("1002"))
                return resultBean.getOption();
        }
        return null;
    }

    //1007四要素验证渠道
    public List<DirtBean.ResultBean.OptionBean> fourElementsChannels() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("1007"))
                return resultBean.getOption();
        }
        return null;
    }

    //1008渠道消费占比
    public List<DirtBean.ResultBean.OptionBean> channelConsumption() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("1008"))
                return resultBean.getOption();
        }
        return null;
    }

}
