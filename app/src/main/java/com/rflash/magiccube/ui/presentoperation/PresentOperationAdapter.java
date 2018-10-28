package com.rflash.magiccube.ui.presentoperation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.operationtoday.ConsumeClick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yangfan on 2017/11/27.
 * 已操作
 */

public class PresentOperationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OperationItem> list;
    private Context context;

    //未还款
    public static final int UN_REPAY = 1;
    //未消费
    public static final int UN_CONSUME = 2;
    //还款
    public static final int REPAY = 3;
    //消费
    public static final int CONSUME = 4;

    // 账单类型
    public static  final String CARD_REPAY = "REPAY"; //还款账单
    public static  final String CARD_MANAGE = "CARD_MANAGE"; //空档期账单

    SimpleDateFormat mSdf = new SimpleDateFormat("MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private AdapterItemOperation mOperationInterface;

    public void setConsumeClick(AdapterItemOperation operation) {
        this.mOperationInterface = operation;
    }

    public PresentOperationAdapter(List<OperationItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View view;
        switch (viewType) {
            case UN_REPAY:
                view = LayoutInflater.from(context).inflate(R.layout.layout_present_unrepay, parent, false);
                vh = new UnRepayHolder(view);
                break;
            case UN_CONSUME:
                view = LayoutInflater.from(context).inflate(R.layout.layout_present_unconsume, parent, false);
                vh = new UnConsumeHolder(view);
                break;
            case REPAY:
                view = LayoutInflater.from(context).inflate(R.layout.layout_present_repay, parent, false);
                vh = new RepayHolder(view);
                break;
            case CONSUME:
                view = LayoutInflater.from(context).inflate(R.layout.layout_present_consume, parent, false);
                vh = new ConsumeHolder(view);
                break;

        }

        return vh;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OperationItem operationItem = list.get(position);

        if (holder instanceof UnRepayHolder) {//未还款
            UnRepayHolder urh = (UnRepayHolder) holder;
            //时间
            try {
                Date parse = sdf.parse(operationItem.getDate());
                urh.setText(R.id.tv_date, mSdf.format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCard = cardNo.substring(cardNo.length() - 4, cardNo.length());
            urh.setText(R.id.tv_bankcard, operationItem.getBankName() + subCard);

            //姓名
            urh.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            urh.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));

            //卡位
            urh.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //账单类型
            String billType =  operationItem.getBillType();
            if(billType !=null){
                switch (billType){
                    case CARD_REPAY:
                        urh.setText(R.id.tv_operation_billtype, "还款账单");
                        break;
                    case CARD_MANAGE:
                        urh.setText(R.id.tv_operation_billtype, "空档期账单");
                        break;
                }
            }

            urh.getView(R.id.ll_repay).setTag(R.id.tv_one_tag, operationItem);
            //还款
            urh.setOnClickListener(R.id.ll_repay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag(R.id.tv_one_tag);
                    mOperationInterface.operation(operationItem);
                }
            });

            //只选中这张卡
            urh.getView(R.id.rl_only_card).setTag(operationItem);
            urh.setOnClickListener(R.id.rl_only_card, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    mOperationInterface.selectTheCard(operationItem);
                }
            });

            //长按
            urh.getView(R.id.ll_repay).setTag(operationItem);
            urh.setOnLongClickListener(R.id.ll_repay, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.i("---onLongClick", "onLongClick");
                    OperationItem operationItem = (OperationItem) v.getTag();
                    mOperationInterface.longClickItem(operationItem);
                    return false;
                }
            });

        } else if (holder instanceof UnConsumeHolder) {//未消费
            UnConsumeHolder uch = (UnConsumeHolder) holder;
            //时间
            try {
                Date parse = sdf.parse(operationItem.getDate());
                uch.setText(R.id.tv_date, mSdf.format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TextView tv_company = uch.getView(R.id.tv_company);
            // 商户、渠道状态不正常，商户已不归属于该养卡点时标红商户
            if("freeze".equals(operationItem.getChannelState()) || "N".equals(operationItem.getMerchantBind())
                    || "freeze".equals(operationItem.getMerchantState())){
                tv_company.setTextColor(context.getResources().getColor(R.color.invalid));
            }else{
                tv_company.setTextColor(context.getResources().getColor(R.color.loginHint));
            }


            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCard = cardNo.substring(cardNo.length() - 4, cardNo.length());
            uch.setText(R.id.tv_bankcard, operationItem.getBankName() + subCard);

            //姓名
            uch.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            uch.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));


            //卡位
            uch.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //公司
            uch.setText(R.id.tv_company, operationItem.getMerchantName());
            //渠道
            uch.setText(R.id.tv_channel, context.getResources().getString(R.string.channel, operationItem.getChannelName()));

            //消费类型
            uch.setText(R.id.tv_consume_type, operationItem.getAccountType());

            uch.getView(R.id.ll_consume).setTag(R.id.tv_one_tag, operationItem);
            //消费
            uch.setOnClickListener(R.id.ll_consume, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag(R.id.tv_one_tag);
                    mOperationInterface.operation(operationItem);
                }
            });

            // 只选择这张卡
            uch.getView(R.id.rl_only_card).setTag(operationItem);
            uch.setOnClickListener(R.id.rl_only_card, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    mOperationInterface.selectTheCard(operationItem);
                }
            });
            //长按
            uch.getView(R.id.ll_consume).setTag(operationItem);
            uch.setOnLongClickListener(R.id.ll_consume, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    mOperationInterface.longClickItem(operationItem);
                    return false;
                }
            });

        } else if (holder instanceof RepayHolder) {//已还款
            RepayHolder rh = (RepayHolder) holder;

            //时间
            try {
                Date parse = sdf.parse(operationItem.getDate());
                rh.setText(R.id.tv_date, mSdf.format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCard = cardNo.substring(cardNo.length() - 4, cardNo.length());
            rh.setText(R.id.tv_bankcard, operationItem.getBankName() + subCard);

            //姓名
            rh.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            rh.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));

            //卡位
            rh.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //还款时间
            rh.setText(R.id.tv_operation_date, operationItem.getOperatorTime());


        } else if (holder instanceof ConsumeHolder) {//已消费
            ConsumeHolder ch = (ConsumeHolder) holder;

            //时间
            try {
                Date parse = sdf.parse(operationItem.getDate());
                ch.setText(R.id.tv_date, mSdf.format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCard = cardNo.substring(cardNo.length() - 4, cardNo.length());
            ch.setText(R.id.tv_bankcard, operationItem.getBankName() + subCard);

            //姓名
            ch.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            ch.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));


            //卡位
            ch.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //公司
            ch.setText(R.id.tv_company, operationItem.getMerchantName());
            //渠道
            ch.setText(R.id.tv_channel, context.getResources().getString(R.string.channel, operationItem.getChannelName()));

            //消费类型
            ch.setText(R.id.tv_consume_type, operationItem.getAccountType());

            //消费时间
            ch.setText(R.id.tv_operation_date, operationItem.getOperatorTime());

        }
    }


    //返回列表类型
    @Override
    public int getItemViewType(int position) {
        int type = 0;
        OperationItem operationItem = list.get(position);
        String state = operationItem.getState();
        String tranType = operationItem.getTranType();
        if (state.equals(Config.NOT_OPERATOR)) {//未操作
            if (tranType.equals(Config.SALE)) {//消费
                type = UN_CONSUME;
            } else if (tranType.equals(Config.REPAY)) {//还款
                type = UN_REPAY;
            }
        } else if (state.equals(Config.DEAL)) {//操作
            if (tranType.equals(Config.SALE)) {//消费
                type = CONSUME;
            } else if (tranType.equals(Config.REPAY)) {//还款
                type = REPAY;
            }
        }
        return type;

    }

    //获取数据长度
    @Override
    public int getItemCount() {
        return list.size();
    }


    //*********************ViewHolder*******************************//

    /**
     * 未 还款
     */
    class UnRepayHolder extends BaseHolder {

        public UnRepayHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 未消费
     */
    class UnConsumeHolder extends BaseHolder {
        public UnConsumeHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 还款
     */
    class RepayHolder extends BaseHolder {
        public RepayHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 还款
     */
    class ConsumeHolder extends BaseHolder {
        public ConsumeHolder(View itemView) {
            super(itemView);
        }
    }


}
