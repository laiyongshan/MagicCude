package com.rflash.magiccube.ui.billcheck;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rflash.basemodule.adapter.BaseAdp;
import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.userportrait.CreditShareItem;

import java.util.List;

/**
 * Created by guobaihui on 2018/03/15.
 */

public class BillCheckAdapter extends BaseAdp<CreditShareItem.ResultBean> {
    public BillCheckAdapter(Context context, List<CreditShareItem.ResultBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    private PersonalMessageListener listener;
    private QueryReportListener reportListener;
    public void setListener(PersonalMessageListener listener) {
        this.listener = listener;
    }
    public void setReportListener(QueryReportListener reportListener) {
        this.reportListener = reportListener;
    }

    @Override
    public void onBind(BaseHolder holder, CreditShareItem.ResultBean userPortraitBean, int position) {

        //姓名
        holder.setText(R.id.tv_user_name, userPortraitBean.getName());

        //身份证号码
        holder.setText(R.id.tv_user_idcard, StringUtil.getBankNo(userPortraitBean.getIdentityCard()));

        //月份
        holder.setText(R.id.tv_date, userPortraitBean.getDate());

        RelativeLayout rlCreditQuery = holder.getView(R.id.rl_credit_query);
        rlCreditQuery.setTag(R.id.tv_one_tag, userPortraitBean);
        rlCreditQuery.setTag(R.id.tv_two_tag, position);

        Button btnQueryReport = holder.getView(R.id.btn_query_report);
        btnQueryReport.setTag(R.id.tv_one_tag, userPortraitBean);
        btnQueryReport.setTag(R.id.tv_two_tag, position);
        // 个人信息
        holder.setOnClickListener(R.id.rl_credit_query, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditShareItem.ResultBean userPortraitBean = (CreditShareItem.ResultBean) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                if (listener != null) {
                    listener.goPersonalMessage(position, userPortraitBean);
                }
            }
        });
        // 报告查看
        holder.setOnClickListener(R.id.btn_query_report,new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreditShareItem.ResultBean userPortraitBean = (CreditShareItem.ResultBean) v.getTag(R.id.tv_one_tag);
                int position = (int) v.getTag(R.id.tv_two_tag);
                if (listener != null) {
                    reportListener.goQueryReport(position, userPortraitBean);
                }
            }
        });
    }


    interface PersonalMessageListener {
        void goPersonalMessage(int position, CreditShareItem.ResultBean userPortraitBean);
    }
    interface QueryReportListener {
        void goQueryReport(int position, CreditShareItem.ResultBean userPortraitBean);
    }
}
