package com.rflash.magiccube.ui.newmain;

import android.content.Context;

import com.rflash.magiccube.ui.salesmen.SalesmenBean;
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
    public final static String key1 = "Dirt";
    public final static String SALEMEN_KEY="salemen";
    Context context;

    public DirtData(Context context) {
        this.context = context;
        list = (List<DirtBean.ResultBean>) CatchManager.getCatchData(context, key1);
        salesList= (List<SalesmenBean.ResultBean>) CatchManager.getCatchData(context,SALEMEN_KEY);
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

    public String[] serviceTypeArr={"固定额度","需还款金额","自定义金额"};//收费基数类型
    public String[] serviceTypeOptions={"FIXED_LIMIT","REPAY_LIMIT","USER_DEFINED"};

    public String[] repayDateArr={"固定还款日","指定天数"};//还款日类型
    public String[] repayDateOptions={"FIXED","APPOINTED_DAYS"};//还款日类型

    public String[] planStateArr={"未操作","已操作"};//规划状态
    public String[] planStateOptionArr={"NOT_OPERATOR","DEAL"};

    public String[] ChannelArr={"请选择渠道号","盛01","汇02","中04"};
    public String[] ChannelIDOptions={"","38","17","42"};

    public String[] ChannelArr2={"请选择渠道号","代付"};
    public String[] ChannelIDOptions2={"","37"};

    public String[] ChannelArr3={"请选择渠道","盛01","汇02","中04","代付"};
    public String[] ChannelIDOptions3={"","38","17","42","37"};

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

    public List<String> planStateList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> planStateOptions = new ArrayList<>();

    public List<String> channelList = new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> channelOptions = new ArrayList<>();

    public List<String> merchantsTypeList = new ArrayList<>();//商户类型
    public List<DirtBean.ResultBean.OptionBean> merchantsTypeOptions = new ArrayList<>();

    public List<String> mccList=new ArrayList<>();
    public List<DirtBean.ResultBean.OptionBean> mccOptions=new ArrayList<>();


    //业务员
    public List<String> salesMenList=new ArrayList<>();
    public List<String> salesIdList=new ArrayList<>();

    List<DirtBean.ResultBean> list;
    List<SalesmenBean.ResultBean> salesList=new ArrayList<>();

    //业务员
    public List<String> getSalesMenList(){
        salesMenList.clear();
        for (SalesmenBean.ResultBean resultBean:salesList){
            salesMenList.add(resultBean.getName()+"");
        }
        salesMenList.add(0,"");
        return salesMenList;
    }

    public List<String> getSalesIdList(){
        salesIdList.clear();
        for (SalesmenBean.ResultBean resultBean:salesList){
            salesIdList.add(resultBean.getId()+"");
        }
        salesIdList.add(0,"");
        return salesIdList;
    }


    //卡片规划状态
    public List<DirtBean.ResultBean.OptionBean> cardPlanState() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("13"))
                return resultBean.getOption();
        }
        return null;
    }

    //18MCC小类
    public List<DirtBean.ResultBean.OptionBean> mcc() {
        for (DirtBean.ResultBean resultBean : list) {
            if (resultBean.getType().getDictTypeId().equals("18"))
                mccOptions = resultBean.getOption();
        }
        return mccOptions;
    }

    public List<String> getMccList() {
        mccList.clear();
        if (mcc() != null) {
            for (DirtBean.ResultBean.OptionBean optionBean : mccOptions) {
                mccList.add(optionBean.getDictName());
            }
        }
        return mccList;
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
        merchantsTypeList.clear();
        if (merchantsType() != null) {
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
        channelList.clear();
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
