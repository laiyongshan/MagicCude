package com.rflash.magiccube.ui.salesmen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
 * @time 2018/10/11 10:25
 * @desc:
 */

public class AddSalesDialog extends Dialog {

    @BindView(R.id.dismiss_dialog_iv)
    ImageView dismiss_dialog_iv;

    @BindView(R.id.cancel_add_tv)
    RoundTextView cancel_add_tv;

    @BindView(R.id.add_salesmen_tv)
    RoundTextView add_salesmen_tv;

    @BindView(R.id.salesmen_name_et)
    EditText salesmen_name_et;

    @BindView(R.id.salesman_profitRatio_et)
    EditText salesman_profitRatio_et;

    Context context;

    interface AddListener {
        void SureListener(String name,String profitRatio);
        void CancleListener();
    }

    AddListener addListener;

    public AddSalesDialog(@NonNull Context context, int themeResId,AddListener addListener) {
        super(context, themeResId);
        setOwnerActivity((Activity) context);
        this.context=context;
        this.addListener=addListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_salesmen);
        ButterKnife.bind(this);
        initView();
    }


    private void initView(){
        setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.dismiss_dialog_iv,R.id.cancel_add_tv,R.id.add_salesmen_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.dismiss_dialog_iv:
                dismiss();
                break;

            case R.id.cancel_add_tv:
                dismiss();
                break;

            case R.id.add_salesmen_tv://新建业务员
                if(salesmen_name_et.getText().toString().trim().equals("")||salesman_profitRatio_et.getText().toString().trim().equals("")){
                    Toast.makeText(context,"请填写完整的信息",Toast.LENGTH_SHORT).show();
                }else{
                    addListener.SureListener(salesmen_name_et.getText().toString().trim(),salesman_profitRatio_et.getText().toString().trim());
                }

                break;
        }
    }


    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }

}
