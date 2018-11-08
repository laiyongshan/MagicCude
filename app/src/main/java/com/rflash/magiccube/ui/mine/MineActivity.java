package com.rflash.magiccube.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/11/7 10:10
 * @desc:
 */

public class MineActivity extends Activity {

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.changePsw_ll)
    LinearLayout changePsw_ll;

    @BindView(R.id.about_us_ll)
    LinearLayout about_us_ll;

    @BindView(R.id.version_ll)
    LinearLayout version_ll;

    @BindView(R.id.exit_ll)
    LinearLayout exit_ll;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
    }

    private void initView(){

    }

    @OnClick({R.id.title_back_tv,R.id.changePsw_ll,R.id.about_us_ll,R.id.version_ll,R.id.exit_ll})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.changePsw_ll:
                Intent intent1=new Intent(MineActivity.this, ChangePasswordActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.about_us_ll:

                break;

            case R.id.version_ll:

                break;

            case R.id.exit_ll:
                Intent intent2=new Intent(MineActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
