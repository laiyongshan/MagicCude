package com.rflash.magiccube.ui.shanghu;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.PositionMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lys
 * @time 2018/10/11 14:24
 * @desc:
 */

public class ShanghuAdapter extends BaseQuickAdapter<ShanghuBean.ResultBean,BaseViewHolder> {

    /**
     * 这个是checkbox的Hashmap集合
     */
<<<<<<< HEAD
//    public final HashMap<Integer, Boolean> map;
=======
    public final HashMap<Integer, Boolean> map;
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec


    boolean isOption;

    public ShanghuAdapter(boolean isOption,List<ShanghuBean.ResultBean> data) {
        super(R.layout.item_shanghu_rv, data);
        this.isOption=isOption;
<<<<<<< HEAD
//        map = new HashMap<>();
//        for (int i=0;i<data.size();i++){
//            map.put(i,false);
//        }
=======
        map = new HashMap<>();
        for (int i=0;i<data.size();i++){
            map.put(i,false);
        }
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec
    }

    /**
     * 全选
     */
    public void selectAll() {
<<<<<<< HEAD
//        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
//        boolean shouldall = false;
//        for (Map.Entry<Integer, Boolean> entry : entries) {
//            Boolean value = entry.getValue();
//            if (!value) {
//                shouldall = true;
//                break;
//            }
//        }
//        for (Map.Entry<Integer, Boolean> entry : entries) {
//            entry.setValue(shouldall);
//        }
        notifyDataSetChanged();
    }

//    /**
//     * 反选
//     */
//    public void neverAll() {
//        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
//        for (Map.Entry<Integer, Boolean> entry : entries) {
//            entry.setValue(!entry.getValue());
//        }
//        notifyDataSetChanged();
//    }

//    /**
//     * 单选
//     * @param postion
//     */
//    public void singleSelect(int postion) {
//        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
//        for (Map.Entry<Integer, Boolean> entry : entries) {
//            entry.setValue(false);
//        }
//        map.put(postion, true);
//        notifyDataSetChanged();
//    }
=======
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        boolean shouldall = false;
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            if (!value) {
                shouldall = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldall);
        }
        notifyDataSetChanged();
    }

    /**
     * 反选
     */
    public void neverAll() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    /**
     * 单选
     * @param postion
     */
    public void singleSelect(int postion) {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(postion, true);
        notifyDataSetChanged();
    }
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec


    @Override
    protected void convert(BaseViewHolder helper, ShanghuBean.ResultBean item) {
        if(isOption)
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.VISIBLE);
        else
            ((CheckBox)helper.getView(R.id.shanghu_cb)).setVisibility(View.GONE);

<<<<<<< HEAD
        ((CheckBox)helper.getView(R.id.shanghu_cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //刷新适配器
//                notifyDataSetChanged();
                EventBus.getDefault().post(new PositionMessage(helper.getPosition(),b));
            }
        });

       //从map集合获取状态
         ((CheckBox)helper.getView(R.id.shanghu_cb)).setChecked(item.getSelected());
=======
        ((CheckBox)helper.getView(R.id.shanghu_cb)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(helper.getPosition(), !map.get(helper.getPosition()));
                //刷新适配器
                notifyDataSetChanged();
                EventBus.getDefault().post(new PositionMessage(helper.getPosition(),map.get(helper.getPosition())));
            }
        });


        //从map集合获取状态
        ((CheckBox)helper.getView(R.id.shanghu_cb)).setChecked(map.get(helper.getPosition()));
>>>>>>> 5c64c07fc2b402943511b72cdfc0a5fec84549ec

        ((TextView)helper.getView(R.id.merchantName_tv)).setText(item.getMerchantName()+"");
        if(item.getState().equals("valid"))
            ((TextView)helper.getView(R.id.state_tv)).setText("正常");
        else if(item.getState().equals("freeze"))
            ((TextView)helper.getView(R.id.state_tv)).setText("冻结");
        ((TextView)helper.getView(R.id.mcc_tv)).setText(item.getMcc()+"");
        ((TextView)helper.getView(R.id.channelName_tv)).setText(item.getChannelName()+"");
        ((TextView)helper.getView(R.id.merchantType_tv)).setText(item.getMerchantTypeName()+"");
        ((TextView)helper.getView(R.id.merchantCode_tv)).setText(item.getMerchantCode()+"");
        ((TextView)helper.getView(R.id.download_date_tv)).setText(item.getDate()+"");
    }
}
