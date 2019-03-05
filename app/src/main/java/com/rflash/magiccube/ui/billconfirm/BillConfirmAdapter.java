package com.rflash.magiccube.ui.billconfirm;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundTextView;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanActivity;
import com.rflash.magiccube.util.DateUtil;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/10.
 */

public class BillConfirmAdapter extends BaseQuickAdapter<BillConfirmBean.ResultBean, BaseViewHolder> {

    BillConfirmPresenter billConfirmPresenter;
    MVPBaseFragment mvpBaseFragment;

    public BillConfirmAdapter(MVPBaseFragment mvpBaseFragment, List<BillConfirmBean.ResultBean> data, BillConfirmPresenter billConfirmPresenter) {
        super(R.layout.item_bill_confirem, data);
        this.billConfirmPresenter = billConfirmPresenter;
        this.mvpBaseFragment = mvpBaseFragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, BillConfirmBean.ResultBean item) {
        if (item.getState().equals("REMIND")) {
            ((TextView) helper.getView(R.id.bill_type_tv)).setText("待确认");
            ((TextView) helper.getView(R.id.bill_type_tv)).setTextColor(Color.RED);
            ((RoundTextView) helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.VISIBLE);
            ((RoundTextView) helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.VISIBLE);
            ((LinearLayout) helper.getView(R.id.factAmt_ll)).setVisibility(View.VISIBLE);
        } else if (item.getState().equals("DEAL")) {
            ((TextView) helper.getView(R.id.bill_type_tv)).setText("已确认");
            ((TextView) helper.getView(R.id.bill_type_tv)).setTextColor(Color.parseColor("#3F51B5"));
            ((RoundTextView) helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.INVISIBLE);
            ((RoundTextView) helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.INVISIBLE);
            ((LinearLayout) helper.getView(R.id.factAmt_ll)).setVisibility(View.GONE);
        } else if (item.getState().equals("IGNORE")) {
            ((TextView) helper.getView(R.id.bill_type_tv)).setText("已忽略");
            ((TextView) helper.getView(R.id.bill_type_tv)).setTextColor(Color.parseColor("#919090"));
            ((RoundTextView) helper.getView(R.id.confirem_bill_rtv)).setVisibility(View.VISIBLE);
            ((RoundTextView) helper.getView(R.id.ignore_bill_rtv)).setVisibility(View.INVISIBLE);
            ((LinearLayout) helper.getView(R.id.factAmt_ll)).setVisibility(View.VISIBLE);
        }

        ((TextView) helper.getView(R.id.bankcard_name_tv)).setText(item.getBankName() + "" + item.getCardNo().substring(item.getCardNo().length() - 4));
        ((TextView) helper.getView(R.id.cardSeqno_tv)).setText(item.getCardSeqno() + "");
        ((TextView) helper.getView(R.id.customerName_tv)).setText(item.getCustomerName() + "");
        if (item.getBillType().equals("REPAY")) {
            ((TextView) helper.getView(R.id.billType_tv)).setText("还款账单");
        } else if (item.getBillType().equals("CARD_MANAGE")) {
            ((TextView) helper.getView(R.id.billType_tv)).setText("精养卡账单");
        }
        ((TextView) helper.getView(R.id.billStart2EndDate_tv)).setText(DateUtil.formatDate1(item.getBillStartDate()) + "至" + DateUtil.formatDate1(item.getBillEndDate()));
        ((TextView) helper.getView(R.id.billAmt_tv)).setText("￥" + StringUtil.getTwoPointString(item.getBillAmt()) + "");
        ((EditText) helper.getView(R.id.the_fact_et)).setText(StringUtil.getTwoPointString(item.getBillAmt()) + "");


        //确认账单
        ((RoundTextView) helper.getView(R.id.confirem_bill_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((EditText) helper.getView(R.id.the_fact_et)).getText().toString().trim().equals("")) {
                    Toast.makeText(mContext, "请输入实际金额！", Toast.LENGTH_SHORT).show();
                } else {
                    final MaterialDialog mMaterialDialog = new MaterialDialog(mContext);
                    mMaterialDialog.setTitle("提示");
                    mMaterialDialog.setMessage("是否确认该账单？");
                    mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mvpBaseFragment instanceof IgnoreFragment) {
                                ((IgnoreFragment) mvpBaseFragment).accurateQueryBill(item.getCardNo(), item.getBillMonth(), "Y", ((EditText) helper.getView(R.id.the_fact_et)).getText().toString().trim());
                            } else if (mvpBaseFragment instanceof RemidFragment) {
                                ((RemidFragment) mvpBaseFragment).accurateQueryBill(item.getCardNo(), item.getBillMonth(), "Y", ((EditText) helper.getView(R.id.the_fact_et)).getText().toString().trim());
                            }
                            mMaterialDialog.dismiss();
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
            }
        });

        //忽略账单
        ((RoundTextView) helper.getView(R.id.ignore_bill_rtv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialDialog mMaterialDialog1 = new MaterialDialog(mContext);
                mMaterialDialog1.setTitle("提示");
                mMaterialDialog1.setMessage("是否忽略该账单？");
                mMaterialDialog1.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        billConfirmPresenter.ignoreCardBill(item.getCardNo(), "BILL_CONFIRM");
                        mMaterialDialog1.dismiss();
                    }
                });
                mMaterialDialog1.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog1.dismiss();
                    }
                });
                mMaterialDialog1.show();
            }
        });


    }
}
