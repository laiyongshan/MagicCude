package com.rflash.magiccube.ui.queryPlanning;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.SignUtil;
import com.rflash.magiccube.util.ToolUtils;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/6 17:20
 * @desc:
 */

public class PlanningAdapter extends BaseQuickAdapter<PlaningBean.ResultBean, BaseViewHolder> {

    boolean isOption;

    /**
     * 全选
     */
    public void selectAll() {
        notifyDataSetChanged();
    }

    public PlanningAdapter(boolean isOption, List<PlaningBean.ResultBean> data) {
        super(R.layout.item_planning_rv, data);
        this.isOption = isOption;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlaningBean.ResultBean item) {

        if (isOption)
            ((CheckBox) helper.getView(R.id.planning_cb)).setVisibility(View.VISIBLE);
        else
            ((CheckBox) helper.getView(R.id.planning_cb)).setVisibility(View.GONE);

        ((ImageView) helper.getView(R.id.bank_icon_iv)).setImageResource(ToolUtils.getBankIcon(item.getBankName()));
        ((TextView) helper.getView(R.id.bank_tv)).setText("" + item.getBankName() + item.getCardNo().substring(item.getCardNo().length() - 4) + "(" + item.getCustomerName() + ")");
        if(item.getState().equals("NOT_OPERATOR")) {
            ((TextView) helper.getView(R.id.state_tv)).setText("未操作");
            ((TextView) helper.getView(R.id.state_tv)).setTextColor(Color.RED);
        }else if(item.getState().equals("DEAL")){
            ((TextView) helper.getView(R.id.state_tv)).setText("已操作");
            ((TextView) helper.getView(R.id.state_tv)).setTextColor(Color.parseColor("#3F51B5"));
        }else if(item.getState().equals("ABANDONED")){
            ((TextView) helper.getView(R.id.state_tv)).setText("废弃");
            ((TextView) helper.getView(R.id.state_tv)).setTextColor(Color.RED);
        }
        ((TextView) helper.getView(R.id.channelName_tv)).setText("" + item.getChannelName());
        ((TextView) helper.getView(R.id.merchantName_tv)).setText("" + item.getMerchantName());
        if (item.getPlanSource().equals("SYSTEM")) {
            ((TextView) helper.getView(R.id.planSource_tv)).setText("系统生成" );
        } else if (item.getPlanSource().equals("INPUT")) {
            ((TextView) helper.getView(R.id.planSource_tv)).setText("补录" );
        }
        ((TextView) helper.getView(R.id.syncStateName_tv)).setText(""+item.getSyncStateName());
        ((TextView) helper.getView(R.id.date_tv)).setText(""+item.getDate());
        ((TextView) helper.getView(R.id.operatorTime_tv)).setText(""+item.getOperatorTime());
        ((TextView) helper.getView(R.id.modifyTime_tv)).setText(""+item.getModifyTime());

        ((LinearLayout)helper.getView(R.id.planning_bottom_rll)).setBackgroundResource(ToolUtils.getPlanItemCardBg(item.getBankName()));

        ((TextView) helper.getView(R.id.cardSeqno_tv)).setText(""+item.getCardSeqno());
        if(item.getBillType()!=null&&item.getBillType().equals("REPAY")) {
            ((TextView) helper.getView(R.id.billType_tv)).setText("还款账单");
        }else if(item.getBillType()!=null&&item.getBillType().equals("CARD_MANAGE")){
            ((TextView) helper.getView(R.id.billType_tv)).setText("空闲账单");
        }
        ((TextView) helper.getView(R.id.accountType_tv)).setText(""+item.getAccountType());
        ((TextView) helper.getView(R.id.amt_tv)).setText("￥"+ StringUtil.getTwoPointString(item.getAmt()));
    }
}
