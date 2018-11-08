package com.rflash.magiccube.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.roundview.RoundTextView;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.login.LoginActivity;
import com.rflash.magiccube.view.SuccessProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/7 10:11
 * @desc:
 */

public class ChangePasswordActivity extends MVPBaseActivity<ChangePswContract.View, ChangePswPrsenter> implements ChangePswContract.View {

    @BindView(R.id.back_iv)
    ImageView back_iv;

    @BindView(R.id.userName_tv)
    TextView userName_tv;

    @BindView(R.id.old_password_et)
    EditText old_password_et;

    @BindView(R.id.new_password_et)
    EditText new_password_et;

    @BindView(R.id.agine_password_et)
    EditText agine_password_et;

    @BindView(R.id.change_password_rtv)
    RoundTextView change_password_rtv;

    SuccessProgressDialog successProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        userName_tv.setText(" "+SpUtil.getString(this, Config.ACCOUNT,"")+" ");
    }

    @OnClick({R.id.back_iv, R.id.change_password_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.change_password_rtv:
                if (old_password_et.getText().toString().trim().equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                } else if (new_password_et.getText().toString().trim().equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                }else if(new_password_et.getText().toString().trim().length()<8){
                    Toast.makeText(ChangePasswordActivity.this, "密码位数不得少于8位", Toast.LENGTH_SHORT).show();
                } else if (agine_password_et.getText().toString().trim().equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, "请再次确认新密码", Toast.LENGTH_SHORT).show();
                } else if (!new_password_et.getText().toString().trim().equals(agine_password_et.getText().toString().trim())) {
                    Toast.makeText(ChangePasswordActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.changePsw(old_password_et.getText().toString().trim(), new_password_et.getText().toString().trim());
                }
                break;
        }
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
    public void getDataSuccess() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                Toast.makeText(ChangePasswordActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                intent.putExtra("fromType",1);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
