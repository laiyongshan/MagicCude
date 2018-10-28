package com.rflash.magiccube.ui.planningquery;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.intelligentplanning.IntelligentPlanningActivity;

import java.util.List;

/**
 * Created by yangfan on 2017/11/17.
 */
@Deprecated
public class PlanningQueryAdapter extends BaseAdp<PlanningCard> {


    public PlanningQueryAdapter(Context context, List<PlanningCard> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(BaseHolder holder, PlanningCard planningCard, int position) {
        //卡号
        ((TextView) holder.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, planningCard.getCardNum()));

        //姓名 和卡号
        ((TextView) holder.getView(R.id.tv_name_and_card)).setText(context.getResources().getString(R.string.name_and_card, planningCard.getName(), planningCard.getBankCard()));

        //银行
        ((TextView) holder.getView(R.id.tv_bank)).setText(planningCard.getBank());

        //服务起止
        ((TextView) holder.getView(R.id.tv_service_start_end)).setText(context.getResources().getString(R.string.service_start_end, planningCard.getStartDate(), planningCard.getEndDate()));

        //账单日  还款日
        ((TextView) holder.getView(R.id.tv_repay_date)).setText(context.getResources().getString(R.string.repay_date, planningCard.getBillDate(), planningCard.getRepayDate()));

        holder.setOnClickListener(R.id.tv_query, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityIntent.readyGo(context, IntelligentPlanningActivity.class);
            }
        });
    }
}
