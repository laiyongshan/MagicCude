package com.rflash.magiccube.ui.salesmen;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.util.DateUtil;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * @author lys
 * @time 2018/10/11 09:33
 * @desc:
 */

public class SalesMenAdapter extends BaseQuickAdapter<SalesmenBean.ResultBean,BaseViewHolder> {

    UpdataSalesDialog updataSalesDialog;

    SalesMenActivity activity;

    public SalesMenAdapter(SalesMenActivity activity,List<SalesmenBean.ResultBean> data) {
        super(R.layout.item_salesmen_rv, data);
        this.activity=activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesmenBean.ResultBean item) {

        ((TextView)helper.getView(R.id.sales_serinum_tv)).setText(item.getId()+"");
        ((TextView)helper.getView(R.id.sales_man_name_tv)).setText(item.getName()+"");
        ((TextView)helper.getView(R.id.profitRatio_tv)).setText(Float.valueOf(item.getProfitRatio())*100+"%");
        ((TextView)helper.getView(R.id.modifyTime_tv)).setText(DateUtil.mills2Date2(item.getModifyTime())+"");

        ((RoundTextView)helper.getView(R.id.update_sales_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataSalesDialog=new UpdataSalesDialog(activity, R.style.Dialog,item, new UpdataSalesDialog.UpdateSalesListener() {
                    @Override
                    public void updataSalesListener(String name, String profitRatio) {
                        activity.operaSalesMan("UPDATE",item.getId()+"",name,profitRatio);
                        updataSalesDialog.dismiss();
                    }
                });
                if(!updataSalesDialog.isShowing())
                    updataSalesDialog.show();
            }
        });

        ((RoundTextView)helper.getView(R.id.delete_sales_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(activity);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("确定删除当前数据？");
                mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        activity.deleteSalesMan(item.getId()+"",item.getName());
                    }
                });
                mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
                mMaterialDialog.show();
            }
        });
    }


}
