package com.rflash.magiccube.ui.other;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;

import java.util.List;


/**
 * Created by lenovo on 2018/10/7.
 */

public class OtherBusinessAdapter extends BaseQuickAdapter<OtherBean,BaseViewHolder> {

    public OtherBusinessAdapter(List<OtherBean> data) {
        super(R.layout.item_other_business, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherBean item) {
        ((ImageView)helper.getView(R.id.item_icon_iv)).setImageResource(item.getImgId());
        ((TextView)helper.getView(R.id.item_other_business_tv)).setText(item.getName());
        ((RoundTextView)helper.getView(R.id.item_go_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
