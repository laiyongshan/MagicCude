package com.rflash.magiccube.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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

    @BindView(R.id.wechartpay_rtv)
    RoundTextView wechartpay_rtv;

    @BindView(R.id.alipay_rtv)
    RoundTextView alipay_rtv;

    interface PayListener {
        void wechartPay();

        void alipay();
    }

    PayListener payListener;

    public PayDialog(@NonNull Context context, int themeResId, PayListener payListener) {
        super(context, themeResId);
        this.payListener = payListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dismiss_iv, R.id.wechartpay_rtv, R.id.alipay_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.dismiss_iv:
                dismiss();
                break;

            case R.id.wechartpay_rtv:
                payListener.wechartPay();
                break;

            case R.id.alipay_rtv:
                payListener.alipay();
                break;
        }
    }

}
