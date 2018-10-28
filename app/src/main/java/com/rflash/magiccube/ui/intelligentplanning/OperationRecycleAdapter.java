package com.rflash.magiccube.ui.intelligentplanning;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rflash.basemodule.adapter.BaseHolder;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.operationtoday.ConsumeClick;

import java.util.List;

/**
 * Created by yangfan on 2017/11/16.
 * recycleview 适配器
 */
@Deprecated
public class OperationRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OperationToday> list;
    private Context context;

    //未操作 还款
    public static final int UN_OPERATION_REPAY = 1;
    //未操作 消费
    public static final int UN_OPERATION_CONSUME = 2;
    //已操作 还款
    public static final int OPERATION_REPAY = 3;
    //已操作 消费
    public static final int OPERATION_CONSUME = 4;



    private ConsumeClick consumeClick;

    public void setConsumeClick(ConsumeClick consumeClick) {
        this.consumeClick = consumeClick;
    }

    public OperationRecycleAdapter(List<OperationToday> list, Context context) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OperationToday operationToday = null;
        operationToday = list.get(position);
        if (holder instanceof UnOperationRepayHolder) {//未操作还款
            UnOperationRepayHolder uorh = (UnOperationRepayHolder) holder;
            //时间
            ((TextView) uorh.getView(R.id.tv_date)).setText(operationToday.getDate());

            //银行卡号
            ((TextView) uorh.getView(R.id.tv_bankcard)).setText(operationToday.getBankCard());

            //银行
            ((TextView) uorh.getView(R.id.tv_bank)).setText(operationToday.getBank());

            //姓名
            ((TextView) uorh.getView(R.id.tv_name)).setText(operationToday.getName());

            //金额
            ((TextView) uorh.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money, operationToday.getMoney()));

            //卡号
            ((TextView) uorh.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, operationToday.getCardNum()));

            uorh.getView(R.id.tv_repay).setTag(operationToday.getMoney());
            //还款
            uorh.setOnClickListener(R.id.tv_repay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String money = (String) v.getTag();
//                    consumeClick.onRepay(money);
                }
            });

        } else if (holder instanceof UnOperationConsumeHolder) {//未操作消费
            UnOperationConsumeHolder uoch = (UnOperationConsumeHolder) holder;
            //时间
            ((TextView) uoch.getView(R.id.tv_date)).setText(operationToday.getDate());

            //银行卡号
            ((TextView) uoch.getView(R.id.tv_bankcard)).setText(operationToday.getBankCard());

            //银行
            ((TextView) uoch.getView(R.id.tv_bank)).setText(operationToday.getBank());

            //姓名
            ((TextView) uoch.getView(R.id.tv_name)).setText(operationToday.getName());

            //金额
            ((TextView) uoch.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money, operationToday.getMoney()));


            //卡号
            ((TextView) uoch.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, operationToday.getCardNum()));

            //公司
            ((TextView) uoch.getView(R.id.tv_company)).setText(operationToday.getCompany());

            //消费类型
            ((TextView) uoch.getView(R.id.tv_consume_type)).setText(operationToday.getConsumType());
            uoch.getView(R.id.tv_consume).setTag(operationToday.getMoney());
            //消费
            uoch.setOnClickListener(R.id.tv_consume, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consumeClick != null) {
                        String money = (String) v.getTag();
//                        consumeClick.onConsumeClick(money);
                    }
                }
            });
        } else if (holder instanceof OperationRepayHolder) {//已操作还款
            OperationRepayHolder orh = (OperationRepayHolder) holder;
            //时间
            ((TextView) orh.getView(R.id.tv_date)).setText(operationToday.getDate());

            //银行卡号
            ((TextView) orh.getView(R.id.tv_bankcard)).setText(operationToday.getBankCard());

            //银行
            ((TextView) orh.getView(R.id.tv_bank)).setText(operationToday.getBank());

            //姓名
            ((TextView) orh.getView(R.id.tv_name)).setText(operationToday.getName());

            //金额
            ((TextView) orh.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money, operationToday.getMoney()));

            //卡号
            ((TextView) orh.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, operationToday.getCardNum()));

            //还款时间
            ((TextView) orh.getView(R.id.tv_date_repay)).setText(context.getResources().getString(R.string.date, operationToday.getOperationDate()));

        } else if (holder instanceof OperationConsumeHolder) {//已操作消费
            OperationConsumeHolder och = (OperationConsumeHolder) holder;
            //时间
            ((TextView) och.getView(R.id.tv_date)).setText(operationToday.getDate());

            //银行卡号
            ((TextView) och.getView(R.id.tv_bankcard)).setText(operationToday.getBankCard());

            //银行
            ((TextView) och.getView(R.id.tv_bank)).setText(operationToday.getBank());

            //姓名
            ((TextView) och.getView(R.id.tv_name)).setText(operationToday.getName());

            //金额
            ((TextView) och.getView(R.id.tv_money)).setText(context.getResources().getString(R.string.money, operationToday.getMoney()));

            //卡号
            ((TextView) och.getView(R.id.tv_num)).setText(context.getResources().getString(R.string.card_num, operationToday.getCardNum()));

            //公司
            ((TextView) och.getView(R.id.tv_company)).setText(operationToday.getCompany());

            //消费类型
            ((TextView) och.getView(R.id.tv_consume_type)).setText(operationToday.getConsumType());

            //消费时间
            ((TextView) och.getView(R.id.tv_date_consume)).setText(context.getResources().getString(R.string.date_consume, operationToday.getOperationDate()));

        }
    }


    //返回列表类型
    @Override
    public int getItemViewType(int position) {
        int type = 0;
        String operationType = list.get(position).getOperationType();
        switch (operationType) {
            case "1":
                type = UN_OPERATION_REPAY;
                break;
            case "2":
                type = UN_OPERATION_CONSUME;
                break;
            case "3":
                type = OPERATION_REPAY;
                break;
            case "4":
                type = OPERATION_CONSUME;
                break;
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
