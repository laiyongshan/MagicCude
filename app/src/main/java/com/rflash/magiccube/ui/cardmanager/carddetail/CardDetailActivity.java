package com.rflash.magiccube.ui.cardmanager.carddetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.magiccube.R;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardBean;
import com.rflash.magiccube.ui.cardmanager.addplan.AddPlanActivity;
import com.rflash.magiccube.ui.cardmanager.cardbase.CardBaseInforActivity;
import com.rflash.magiccube.ui.cardmanager.cardbill.CardBillActivity;
import com.rflash.magiccube.ui.cardmanager.cardcharge.CardChargeActivity;
import com.rflash.magiccube.ui.cardmanager.increase.CardIncreaseActivity;
import com.rflash.magiccube.ui.renewal.RenewalActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by lenovo on 2018/10/20.
 */

public class CardDetailActivity extends MVPBaseActivity<CardDetailContract.View,CardDetailPresenter> implements CardDetailContract.View{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.card_type_bg)
    ImageView card_type_bg;

    @BindViews({R.id.card_baseinfo_tv,R.id.card_bill_tv,R.id.card_increase_rv,R.id.card_charge_tv,
    R.id.freeze_card_tv,R.id.renewal_card_tv,R.id.add_consume_refund_tv,R.id.delete_card_tv})
    TextView[] tvs;

    @BindView(R.id.freeze_icon_iv)
    ImageView freeze_icon_iv;

    CardBean.ResultBean cardDetailBean;
    Intent intent;

    String cardNo="";
    String cardState="";//VALID: 正常   FREEZE：冻结   EXPIRE:过期
    String VALID="VALID";
    String FREEZE="FREEZE";
    String EXPIRE="EXPIRE";
    String DELETE="DELETE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        initView();
    }

    private void initView(){
        cardDetailBean= (CardBean.ResultBean) getIntent().getSerializableExtra("cardDetail");

        if(cardDetailBean!=null){

            cardNo=cardDetailBean.getCardNo()+"";
            cardState=cardDetailBean.getState()+"";

            if(cardState.equals("VALID")){//正常
                card_type_bg.setVisibility(View.GONE);
                freeze_icon_iv.setImageResource(R.mipmap.icon_dongjie);
            }else if(cardState.equals("FREEZE")){//冻结
                card_type_bg.setVisibility(View.VISIBLE);
                freeze_icon_iv.setImageResource(R.mipmap.icon_card_jiedong);
            }else if(cardState.equals("EXPIRE")){//过期
                card_type_bg.setVisibility(View.VISIBLE);
                freeze_icon_iv.setImageResource(R.mipmap.icon_dongjie);
            }

            ((TextView)findViewById(R.id.bank_name_tv)).setText(cardDetailBean.getCardBankName()+"");
            ((TextView)findViewById(R.id.cardowner_name_tv)).setText("持卡人:"+cardDetailBean.getCustomerName()+"");
            ((TextView)findViewById(R.id.bank_card_num_tv)).setText(cardDetailBean.getCardNo()+"");
            ((TextView)findViewById(R.id.card_seri_num_tv)).setText(cardDetailBean.getCardSeqno()+"");
            ((TextView)findViewById(R.id.billDate_tv)).setText(cardDetailBean.getBillDate()+"");
            ((TextView)findViewById(R.id.repayDate_tv)).setText(cardDetailBean.getRepayDate()+"");
            ((TextView)findViewById(R.id.availableAmt_tv)).setText(cardDetailBean.getAvailableAmt()+"");
            ((TextView)findViewById(R.id.salesMan_tv)).setText(cardDetailBean.getSalesMan()+"");
            ((TextView)findViewById(R.id.serviceEndDate_tv)).setText("服务到期时间："+cardDetailBean.getServiceEndDate()+"");
            ((TextView)findViewById(R.id.fixedLimit_tv)).setText("固定额度：￥"+cardDetailBean.getFixedLimit()+"");
            ((TextView)findViewById(R.id.initAmt_tv)).setText("初始金额：￥"+cardDetailBean.getInitAmt()+"");
        }
    }

    @OnClick({R.id.title_back_tv,R.id.card_baseinfo_tv,R.id.card_bill_tv,R.id.card_increase_rv,R.id.card_charge_tv,R.id.xuqi_ll,
            R.id.freeze_ll,R.id.renewal_card_tv,R.id.add_consume_refund_tv,R.id.delete_card_tv})
    public void click(View view){
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.card_baseinfo_tv:
                intent=new Intent(CardDetailActivity.this,CardBaseInforActivity.class);
                intent.putExtra("cardDetail",cardDetailBean);
                startActivity(intent);
                break;

            case R.id.card_bill_tv:
                intent=new Intent(CardDetailActivity.this,CardBillActivity.class);
                intent.putExtra("cardDetail",cardDetailBean);
                startActivity(intent);
                break;

            case R.id.card_increase_rv:
                intent=new Intent(CardDetailActivity.this,CardIncreaseActivity.class);
                intent.putExtra("cardDetail",cardDetailBean);
                startActivity(intent);
                break;

            case R.id.card_charge_tv:
                intent=new Intent(CardDetailActivity.this,CardChargeActivity.class);
                intent.putExtra("cardDetail",cardDetailBean);
                startActivity(intent);
//                ActivityIntent.readyGo(CardDetailActivity.this,CardChargeActivity.class);
                break;

            case R.id.freeze_ll://凍結
                if(cardState.equals("VALID")){//冻结
                    freezeCard();
                }else if(cardState.equals("FREEZE")){//解冻
                    validCard();
                }else if(cardState.equals("EXPIRE")){//过期
                    Toast.makeText(CardDetailActivity.this,"卡片过期，无法进行该操作",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.xuqi_ll://續期
                if(cardState.equals("VALID")||cardState.equals("EXPIRE")){//正常或过期
                    intent=new Intent(CardDetailActivity.this,RenewalActivity.class);
                    intent.putExtra("cardDetail",cardDetailBean);
                    startActivity(intent);
                }else if(cardState.equals("FREEZE")){//冻结
                    Toast.makeText(CardDetailActivity.this,"卡片冻结，无法进行该操作",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.add_consume_refund_tv://添加交易
                if(cardState.equals("VALID")){//正常
                    intent=new Intent(CardDetailActivity.this,AddPlanActivity.class);
                    intent.putExtra("cardDetail",cardDetailBean);
                    startActivity(intent);
                }else if(cardState.equals("FREEZE")){//冻结
                    Toast.makeText(CardDetailActivity.this,"卡片冻结，无法进行该操作",Toast.LENGTH_SHORT).show();
                }else if(cardState.equals("EXPIRE")){//过期
                    Toast.makeText(CardDetailActivity.this,"卡片过期，无法进行该操作",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.delete_card_tv://刪除卡片
                deleteCard();
                break;
        }
    }

    //冻结卡片
    private void freezeCard(){
        final MaterialDialog mMaterialDialog = new MaterialDialog(CardDetailActivity.this);
        mMaterialDialog.setTitle("提示");
        mMaterialDialog.setMessage("确定冻结该卡片吗？");
        mMaterialDialog.setPositiveButton("冻结",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
                updateCardState(FREEZE);
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

    //解冻卡片，恢复正常
    private void validCard(){
        final MaterialDialog mMaterialDialog = new MaterialDialog(CardDetailActivity.this);
        mMaterialDialog.setTitle("提示");
        mMaterialDialog.setMessage("确定解冻该卡片吗？");
        mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
                updateCardState(VALID);
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

    DeleteCardDialog deleteCardDialog;
    //删除卡片
    private void deleteCard(){
        final MaterialDialog mMaterialDialog = new MaterialDialog(CardDetailActivity.this);
        mMaterialDialog.setTitle("提示");
        mMaterialDialog.setMessage("删除卡片后，卡片数据将无法恢复！");
        mMaterialDialog.setPositiveButton("确定",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
                deleteCardDialog=new DeleteCardDialog(CardDetailActivity.this, R.style.Dialog, new DeleteCardDialog.DeleteCardListener() {
                    @Override
                    public void delete() {
                        deleteCardDialog.dismiss();
                        updateCardState(DELETE);
                    }
                });
                deleteCardDialog.show();
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

    private void updateCardState(String state){
        mPresenter.updateCardState(cardNo,state);
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void updateCardState(BaseBean baseBean) {

    }


}
