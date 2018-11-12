package com.rflash.magiccube.ui.addcard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/8 14:22
 * @desc:
 */

public class PayDialog extends Dialog {

    @BindView(R.id.dismiss_iv)
    ImageView dismiss_iv;
    @BindView(R.id.num_et)
    EditText num_et;

    @BindView(R.id.amt_tv)
    TextView amt_tv;

    @BindView(R.id.wechartpay_rtv)
    RoundTextView wechartpay_rtv;

    @BindView(R.id.alipay_rtv)
    RoundTextView alipay_rtv;

    public interface PayListener {
        void wechartPay(String num);

        void alipay(String num);
    }

    PayListener payListener;

    Context context;

    public PayDialog(@NonNull Context context, int themeResId, PayListener payListener) {
        super(context, themeResId);
        this.payListener = payListener;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);

        num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null&&editable.toString().length()>0)
                    amt_tv.setText("￥"+editable.toString());
                else
                    amt_tv.setText("0");
            }
        });
    }

    @OnClick({R.id.dismiss_iv, R.id.wechartpay_rtv, R.id.alipay_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.dismiss_iv:
                dismiss();
                break;

            case R.id.wechartpay_rtv:
                if(!num_et.getText().toString().trim().equals("")||!num_et.getText().toString().trim().equals("0"))
                    payListener.wechartPay(num_et.getText().toString().trim());
                else
                    Toast.makeText(context,"请输入购买数量",Toast.LENGTH_SHORT).show();
                break;

            case R.id.alipay_rtv:
                if(!num_et.getText().toString().trim().equals("")||!num_et.getText().toString().trim().equals("0"))
                    payListener.alipay(num_et.getText().toString().trim());
                else
                    Toast.makeText(context,"请输入购买数量",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
