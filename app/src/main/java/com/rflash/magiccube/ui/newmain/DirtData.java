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
<<<<<<< HEAD
        list = (List<DirtBean.ResultBean>) CatchManager.getCatchData(context, key);
    }

    public String[] tranTypeArr1 = {"请选择操作类型","消费", "还款"};//操作类型
    public String[] tranTypeOptions1={"","SALE","REPAY"};

    public String[] tranTypeArr = {"消费", "还款"};//交易类型
    public String[] tranTypeOptions={"SALE","REPAY"};

    public String[] accountTypeArr1 = {"请选择到帐类型","T0", "T1"};//到帐类型
    public String[] accountTypeOptions1={"","T0","T1"};

    public String[] stateArr= {"请选择操作状态","未操作", "已操作","废弃"};//操作状态
    public String[] stateOptions={"","NOT_OPERATOR","DEAL","ABANDONED"};


    public String[] ShanghuStateArr = {"请选择商户状态","正常", "冻结"};//商户类型
    public String[] ShanghuStateOptions={"","vaild","freeze"};

    public String[] cardStateArr = {"请选择状态","正常", "冻结","过期"};//卡片类型
    public String[] cardStateOptions={"","VALID","FREEZE","EXPIRE"};

    public String[] isNeedT0Arr={"是","否"};//是否需要T0
    public String[] isNeedOptions={"Y","N"};

    public String[] serviceTypeArr={"固定信用额度","需还款金额","自定义金额"};//收费基数类型
    public String[] serviceTypeOptions={"FIXED_LIMIT","REPAY_LIMIT","USER_DEFINED"};

    public String[] repayDateArr={"固定还款日","指定天数"};//还款日类型
    public String[] repayDateOptions={"FIXED","APPOINTED_DAYS"};//还款日类型

    public String[] cardMediaArr={"IC卡","磁条卡"};//卡介质
    public String[] cardMediaOptions={"IC_CARD","STRIP_CARD"};

    public String[] isHolidayArr={"是","否"};//节假日规划
    public String[] isHolidayOptions={"Y","N"};

    public String[] isFreePlanArr={"是","否"};//空闲时间规划
    public String[] isFreePlanOptions={"Y","N"};

    public String[] isHaveKeyArr={"请选择","是","否"};//是否有规划
    public String[] isHaveKeyOptions={"","Y","N"};

    public String[] isOrNoArr={"是","否"};//是否
    public String[] isOrNoOptions={"Y","N"};

=======
    }

    public String[] tranTypeArr = {"消费", "还款"};//交易类型
    public String[] tranTypeOptions={"SALE","REPAY"};

    public String[] isNeedT0Arr={"是","否"};//是否需要T0
    public String[] isNeedOptions={"Y","N"};

>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
    public List<String> planStateList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> planStateOptions = new ArrayList<>();

    public List<String> channelList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> channelOptions = new ArrayList<>();

    public List<String> merchantsTypeList = new ArrayList<>();//商户类型
    public List<DirtBean.ResultBean.OptionBean> merchantsTypeOptions = new ArrayList<>();


<<<<<<< HEAD
    List<DirtBean.ResultBean> list;
=======
    List<DirtBean.ResultBean> list = (List<DirtBean.ResultBean>) CatchManager.getCatchData(context, key);
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

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
<<<<<<< HEAD
        merchantsTypeList.clear();
        if (merchantsType() != null) {
=======
        if (channnel() != null) {
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
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
<<<<<<< HEAD
        channelList.clear();
=======
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
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
