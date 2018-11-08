package com.rflash.magiccube.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.rflash.magiccube.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lys
 * @time 2018/5/29 15:22
 * @desc:
 */

public class QrCodeDialog extends Dialog{

    Context context;

    @BindView(R.id.qrcode_iv)
    ImageView qrcode_iv;

    @BindView(R.id.dismiss_dialog_iv)
    ImageView dismiss_dialog_iv;

    Bitmap bitmap;

    public QrCodeDialog(@NonNull Context context, int themeResId, Bitmap bitmap) {
        super(context, themeResId);
        this.context = context;
        this.bitmap=bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_qr_pay);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);

        dismiss_dialog_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if(bitmap!=null)
            qrcode_iv.setImageBitmap(bitmap);
    }

}
