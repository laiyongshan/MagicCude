package com.rflash.magiccube.ui.operationtoday;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.presentoperation.OperationItem;

import java.util.List;

/**
 * Created by yangfan on 2017/11/27.
 * 已操作
 */

public class OperationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OperationItem> list;
    private Context context;

    //未操作 还款
    public static final int UN_OPERATION_REPAY = 1;
    //未操作 消费
    public static final int UN_OPERATION_CONSUME = 2;
    //已操作 还款
    public static final int OPERATION_REPAY = 3;
    //已操作 消费
    public static final int OPERATION_CONSUME = 4;


    private AdapterItemClick consumeClick;

    public void setConsumeClick(AdapterItemClick consumeClick) {
        this.consumeClick = consumeClick;
    }

    public OperationAdapter(List<OperationItem> list, Context context) {
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
            case UN_OPERATION_REPAY:
                view = LayoutInflater.from(context).inflate(R.layout.layout_unoperation_repay, parent, false);
                vh = new UnOperationRepayHolder(view);
                break;
            case UN_OPERATION_CONSUME:
                view = LayoutInflater.from(context).inflate(R.layout.layout_unoperation_consume, parent, false);
                vh = new UnOperationConsumeHolder(view);
                break;
            case OPERATION_REPAY:
                view = LayoutInflater.from(context).inflate(R.layout.layout_operation_repay, parent, false);
                vh = new OperationRepayHolder(view);
                break;
            case OPERATION_CONSUME:
                view = LayoutInflater.from(context).inflate(R.layout.layout_operation_consume, parent, false);
                vh = new OperationConsumeHolder(view);
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

        if (holder instanceof UnOperationRepayHolder) {//未操作还款
            UnOperationRepayHolder uorh = (UnOperationRepayHolder) holder;
            //时间
            uorh.setText(R.id.tv_date, operationItem.getDate());

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCardNo = StringUtil.getBankNo(cardNo);
            uorh.setText(R.id.tv_bankcard, subCardNo);

            //银行
            uorh.setText(R.id.tv_bank, operationItem.getBankName());

            //姓名
            uorh.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            uorh.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));

            //卡位
            uorh.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            uorh.getView(R.id.tv_repay).setTag(R.id.tv_one_tag, operationItem);
            uorh.getView(R.id.tv_repay).setTag(R.id.tv_two_tag, position);
            //还款
            uorh.setOnClickListener(R.id.tv_repay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag(R.id.tv_one_tag);
                    int position = (int) v.getTag(R.id.tv_two_tag);
                    ((ConsumeClick) consumeClick).onRepay(operationItem, position);
                }
            });

            uorh.getView(R.id.ll_content).setTag(operationItem);
            uorh.setOnClickListener(R.id.ll_content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    consumeClick.onContentClick(operationItem);
                }
            });

        } else if (holder instanceof UnOperationConsumeHolder) {//未操作消费
            UnOperationConsumeHolder uoch = (UnOperationConsumeHolder) holder;
            //时间
            uoch.setText(R.id.tv_date, operationItem.getDate());

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCardNo = StringUtil.getBankNo(cardNo);
            uoch.setText(R.id.tv_bankcard, subCardNo);

            //银行
            uoch.setText(R.id.tv_bank, operationItem.getBankName());

            //姓名
            uoch.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            uoch.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));


            //卡位
            uoch.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //公司
            uoch.setText(R.id.tv_company, operationItem.getMerchantName());
            //渠道
            uoch.setText(R.id.tv_channel, operationItem.getChannelName());

            //消费类型
            uoch.setText(R.id.tv_consume_type, operationItem.getAccountType());

            uoch.getView(R.id.tv_consume).setTag(R.id.tv_one_tag, operationItem);
            uoch.getView(R.id.tv_consume).setTag(R.id.tv_two_tag, position);
            //消费
            uoch.setOnClickListener(R.id.tv_consume, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consumeClick != null) {
                        OperationItem operationItem = (OperationItem) v.getTag(R.id.tv_one_tag);
                        int position = (int) v.getTag(R.id.tv_two_tag);
                        ((ConsumeClick) consumeClick).onConsumeClick(operationItem, position);
                    }
                }
            });

            uoch.getView(R.id.ll_content).setTag(operationItem);
            uoch.setOnClickListener(R.id.ll_content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    consumeClick.onContentClick(operationItem);
                }
            });
        } else if (holder instanceof OperationRepayHolder) {//已操作还款
            OperationRepayHolder orh = (OperationRepayHolder) holder;
            //时间
            orh.setText(R.id.tv_date, operationItem.getDate());

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCardNo = StringUtil.getBankNo(cardNo);
            orh.setText(R.id.tv_bankcard, subCardNo);

            //银行
            orh.setText(R.id.tv_bank, operationItem.getBankName());

            //姓名
            orh.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            orh.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));


            //卡位
            orh.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //还款时间
            orh.setText(R.id.tv_date_repay, context.getResources().getString(R.string.date, operationItem.getOperatorTime()));

            orh.getView(R.id.ll_content).setTag(operationItem);
            orh.setOnClickListener(R.id.ll_content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    consumeClick.onContentClick(operationItem);
                }
            });
        } else if (holder instanceof OperationConsumeHolder) {//已操作消费
            OperationConsumeHolder och = (OperationConsumeHolder) holder;
            //时间
            och.setText(R.id.tv_date, operationItem.getDate());

            //银行卡号
            String cardNo = operationItem.getCardNo();
            String subCardNo = StringUtil.getBankNo(cardNo);
            och.setText(R.id.tv_bankcard, subCardNo);

            //银行
            och.setText(R.id.tv_bank, operationItem.getBankName());

            //姓名
            och.setText(R.id.tv_name, operationItem.getCustomerName());

            //金额
            String amt = operationItem.getAmt();
            String amtYuan = StringUtil.getTwoPointString(amt);
            och.setText(R.id.tv_money, context.getResources().getString(R.string.money, amtYuan));


            //卡位
            och.setText(R.id.tv_num, context.getResources().getString(R.string.card_num, operationItem.getCardSeqno()));

            //公司
            och.setText(R.id.tv_company, operationItem.getMerchantName());

            //消费类型
            och.setText(R.id.tv_consume_type, operationItem.getAccountType());

            //消费时间
            och.setText(R.id.tv_date_consume, context.getResources().getString(R.string.date_consume, operationItem.getOperatorTime()));
            och.getView(R.id.ll_content).setTag(operationItem);
            och.setOnClickListener(R.id.ll_content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OperationItem operationItem = (OperationItem) v.getTag();
                    consumeClick.onContentClick(operationItem);
                }
            });
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
                type = UN_OPERATION_CONSUME;
            } else if (tranType.equals(Config.REPAY)) {//还款
                type = UN_OPERATION_REPAY;
            }
        } else if (state.equals(Config.DEAL)) {//操作
            if (tranType.equals(Config.SALE)) {//消费
                type = OPERATION_CONSUME;
            } else if (tranType.equals(Config.REPAY)) {//还款
                type = OPERATION_REPAY;
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
     * 未操作 还款
     */
    class UnOperationRepayHolder extends BaseHolder {

        public UnOperationRepayHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 未操作 消费
     */
    class UnOperationConsumeHolder extends BaseHolder {
        public UnOperationConsumeHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 已操作 还款
     */
    class OperationRepayHolder extends BaseHolder {
        public OperationRepayHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 已操作 还款
     */
    class OperationConsumeHolder extends BaseHolder {
        public OperationConsumeHolder(View itemView) {
            super(itemView);
        }
    }


}
