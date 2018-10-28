package com.rflash.magiccube.ui.billconfirm;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * Created by lenovo on 2018/10/10.
 */

public class BillConfirmAdapter extends BaseQuickAdapter<BillConfirmBean,BaseViewHolder> {

    int billType;

    public BillConfirmAdapter(int billType,List<BillConfirmBean> data) {
        super(R.layout.item_bill_confirem, data);
        this.billType=billType;
    }

    @Override
    protected void convert(BaseViewHolder helper, BillConfirmBean item) {
        if(billType==0){
            ((TextView)helper.getView(R.id.bill_type_tv)).setText("待确认");
            ((TextView)helper.getView(R.id.bill_type_tv)).setTextColor(Color.RED);
            ((RoundTextView)helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.VISIBLE);
            ((RoundTextView)helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.VISIBLE);
        }else if(billType==1){
            ((TextView)helper.getView(R.id.bill_type_tv)).setText("已确认");
            ((TextView)helper.getView(R.id.bill_type_tv)).setTextColor(Color.parseColor("#3F51B5"));
            ((RoundTextView)helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.GONE);
            ((RoundTextView)helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.GONE);
        }else if(billType==2){
            ((TextView)helper.getView(R.id.bill_type_tv)).setText("已忽略");
            ((TextView)helper.getView(R.id.bill_type_tv)).setTextColor(Color.parseColor("#919090"));
            ((RoundTextView)helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.VISIBLE);
            ((RoundTextView)helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.GONE);
        }

        //确认账单
        ((RoundTextView)helper.getView(R.id.confirem_bill_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //忽略账单
        ((RoundTextView)helper.getView(R.id.ignore_bill_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
