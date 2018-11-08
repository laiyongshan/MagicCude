package com.rflash.magiccube.ui.queryPlanning;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * @author lys
 * @time 2018/11/6 17:20
 * @desc:
 */

public class PlanningAdapter  extends BaseQuickAdapter<PlaningBean.ResultBean,BaseViewHolder> {

    public PlanningAdapter(List<PlaningBean.ResultBean> data) {
        super(R.layout.item_planning_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlaningBean.ResultBean item) {

//        ((CheckBox)helper.getView(R.id.planning_cb))
//        ((ImageView)helper.getView(R.id.bank_icon_iv)).setImageResource();
        ((TextView)helper.getView(R.id.bank_tv)).setText("");
        ((TextView)helper.getView(R.id.state_tv)).setText("");
        ((TextView)helper.getView(R.id.channelName_tv)).setText("");
        ((TextView)helper.getView(R.id.merchantName_tv)).setText("");
        ((TextView)helper.getView(R.id.planSource_tv)).setText("");
        ((TextView)helper.getView(R.id.syncStateName_tv)).setText("");
        ((TextView)helper.getView(R.id.date_tv)).setText("");
        ((TextView)helper.getView(R.id.operatorTime_tv)).setText("");
        ((TextView)helper.getView(R.id.modifyTime_tv)).setText("");

//        ((LinearLayout)helper.getView(R.id.planning_bottom_rll))

        ((TextView)helper.getView(R.id.cardSeqno_tv)).setText("");
        ((TextView)helper.getView(R.id.billType_tv)).setText("");
        ((TextView)helper.getView(R.id.accountType_tv)).setText("");
        ((TextView)helper.getView(R.id.amt_tv)).setText("");
    }
}
