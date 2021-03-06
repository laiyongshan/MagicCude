package com.rflash.magiccube.ui.renewalmind;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;
import com.rflash.magiccube.util.ToolUtils;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/7 09:31
 * @desc:
 */

public class RenewalMindAdapter extends BaseQuickAdapter<RenewalMindBean.ResultBean,BaseViewHolder> {

    public RenewalMindAdapter( List<RenewalMindBean.ResultBean> data) {
        super(R.layout.item_renewalmind_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RenewalMindBean.ResultBean item) {

        ((ImageView)helper.getView(R.id.bank_icon)).setImageResource(ToolUtils.getBankIcon(item.getBankName()+""));
        ((TextView)helper.getView(R.id.bank_name_tv)).setText(""+item.getBankName());
        ((TextView)helper.getView(R.id.cardowner_name_tv)).setText("持卡人："+item.getCustomerName());

        ((RelativeLayout)helper.getView(R.id.renewal_mind_rl)).setBackgroundResource(ToolUtils.getFinanceBankCardBg(item.getBankName()+""));

        ((TextView)helper.getView(R.id.cardSeqno_tv)).setText("卡位："+""+item.getCardSeqno());
        ((TextView)helper.getView(R.id.createDate_tv)).setText("创建时间："+""+ DateUtil.mills2Date2(item.getCreateTime()));
        ((TextView)helper.getView(R.id.endDate_tv)).setText("到期时间："+""+DateUtil.formatDate1(item.getServiceEndDate()+""));
        ((TextView)helper.getView(R.id.bank_card_num_tv)).setText(""+item.getCardNo());//卡号
        ((TextView)helper.getView(R.id.reamrk_tv)).setText(""+item.getRemark());//备注
        if(item.getState().equals("REMIND")) {
            ((TextView) helper.getView(R.id.state_tv)).setText("待处理");
            ((RoundTextView)helper.getView(R.id.to_renewal_rtv)).setVisibility(View.VISIBLE);
        }else if(item.getState().equals("IGNORE")){
            ((TextView) helper.getView(R.id.state_tv)).setText("已忽略");
            ((RoundTextView)helper.getView(R.id.to_renewal_rtv)).setVisibility(View.VISIBLE);
        }else if(item.getState().equals("DEAL")){
            ((TextView) helper.getView(R.id.state_tv)).setText("已处理");
            ((TextView) helper.getView(R.id.state_tv)).setTextColor(Color.parseColor("#3368B1"));
//            ((RoundTextView)helper.getView(R.id.to_renewal_rtv)).setVisibility(View.GONE);
        }
    }
}
