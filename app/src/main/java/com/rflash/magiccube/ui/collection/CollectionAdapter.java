package com.rflash.magiccube.ui.collection;

import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;

import java.util.List;

/**
 * @author lys
 * @time 2018/10/8 17:07
 * @desc:
 */

public class CollectionAdapter extends BaseQuickAdapter<CollectionBean,BaseViewHolder> {

    public CollectionAdapter( List<CollectionBean> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        ((CardView)helper.getView(R.id.collection_item_layout)).setCardBackgroundColor(item.getBackgroundColor());
        ((ImageView)helper.getView(R.id.collection_item_logo)).setImageResource(item.getLogoId());
        ((TextView)helper.getView(R.id.collection_stype_tv)).setText(item.getCollectionStype()+"");
    }
}
