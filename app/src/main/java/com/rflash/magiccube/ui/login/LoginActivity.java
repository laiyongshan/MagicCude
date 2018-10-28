package com.rflash.magiccube.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.internal.ListenerClass;


/**
 * 登陆界面
 * <p>
 * 记住密码：true  登录时存储用户信息，下次进入LoginActivity 时，先获取用户信息，然后显示。
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    //用户名图标
    @BindView(R.id.iv_name)
    ImageView ivName;

    //用户名图标右侧竖线
    @BindView(R.id.view_name)
    View viewName;

    //用户名输入框
    @BindView(R.id.edt_name)
    EditText edtName;

    //包裹用户名输入框的LinearLayout
    @BindView(R.id.ll_name)
    LinearLayout llName;

    //密码图标
    @BindView(R.id.iv_pwd)
    ImageView ivPwd;

    //密码图标右侧竖线
    @BindView(R.id.view_pwd)
    View viewPwd;

    //密码输入框
    @BindView(R.id.edt_pwd)
    EditText edtPwd;

    //包裹密码输入框的LinearLayout
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;

    //记住密码CheckBox
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;

    //登录按钮
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.iv_name_dismiss)
    ImageView ivNameDismiss;

    @BindView(R.id.iv_pwd_dismiss)
    ImageView ivPwdDismiss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        boolean checked = SpUtil.getBoolean(this, Config.USER_STORE, false);
        cbRemember.setChecked(checked);
        if (checked) {
            edtName.setText(SpUtil.getString(this, Config.ACCOUNT, ""));
            edtPwd.setText(SpUtil.getString(this, Config.USER_PWD, ""));
        }
    }




    /**
     * 输入框焦点监听
     * 改变输入框的样式
     *
     * @param v
     * @param hasFocus
     */
    @OnFocusChange({R.id.edt_name, R.id.edt_pwd})
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt_name://用户名输入框
                if (hasFocus) {//有焦点
                    ivName.setImageResource(R.mipmap.login_name_foucs);
                    llName.setBackgroundResource(R.drawable.login_selector);
                    viewName.setBackgroundResource(R.color.loginEdit);
                } else {//没有焦点
                    ivName.setImageResource(R.mipmap.login_name_normal);
                    llName.setBackgroundResource(R.drawable.login_un_selector);
                    viewName.setBackgroundResource(R.color.white);
                }
                break;
            case R.id.edt_pwd://密码输入框
                if (hasFocus) {//有焦点
                    ivPwd.setImageResource(R.mipmap.login_pwd_focus);
                    llPwd.setBackgroundResource(R.drawable.login_selector);
                    viewPwd.setBackgroundResource(R.color.loginEdit);
                } else {//没有焦点
                    ivPwd.setImageResource(R.mipmap.login_pwd_normal);
                    llPwd.setBackgroundResource(R.drawable.login_un_selector);
                    viewPwd.setBackgroundResource(R.color.white);
                }
                break;
        }

    }


    /**
     * 点击事件
     */
    @OnClick(R.id.tv_login)
    public void onClickView(View view) {
        String name = edtName.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(Config.VERSION_CODE, SpUtil.getString(this, Config.MACHINECODE, ""), name, pwd);
    }


    /**
     * 登录成功
     * @param name
     * @param pwd
     */
    @Override
    public void loginSuccess(String name, String pwd) {
        boolean checked = cbRemember.isChecked();
        mPresenter.saveUserInfo(checked, name, pwd);
    }

    @OnTextChanged(R.id.edt_name)
    public void onNameTextChanged(CharSequence s, int start, int before, int count){

        if (!TextUtils.isEmpty(s)){
            ivNameDismiss.setVisibility(View.VISIBLE);
        }else {
            ivNameDismiss.setVisibility(View.GONE);
        }

    }

    @OnTextChanged(R.id.edt_pwd)
    public void onPwdTextChanged(CharSequence s, int start, int before, int count){

        if (!TextUtils.isEmpty(s)){
            ivPwdDismiss.setVisibility(View.VISIBLE);
        }else {
            ivPwdDismiss.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.iv_name_dismiss)
    public void nameDismiss(){
        edtName.setText("");
    }

    @OnClick(R.id.iv_pwd_dismiss)
    public void pwdDismiss(){
        edtPwd.setText("");
    }

}
