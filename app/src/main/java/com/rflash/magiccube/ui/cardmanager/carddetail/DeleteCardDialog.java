package com.rflash.magiccube.ui.cardmanager.carddetail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;

import butterknife.ButterKnife;

/**
 * @author lys
 * @time 2018/11/2 14:02
 * @desc:
 */

public class DeleteCardDialog extends Dialog {

    RoundTextView cancel_rtv;
    RoundTextView sure_rtv;

    interface DeleteCardListener{
        void delete();
    }

    DeleteCardListener deleteCardListener;

    public DeleteCardDialog(@NonNull Context context, int themeResId, DeleteCardListener deleteCardListener) {
        super(context, themeResId);
        this.deleteCardListener=deleteCardListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_card);
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView(){
        cancel_rtv=findViewById(R.id.cancel_rtv);
        cancel_rtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        sure_rtv=findViewById(R.id.sure_rtv);
        sure_rtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCardListener.delete();
            }
        });
    }

}
