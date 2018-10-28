package com.rflash.magiccube;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.rflash.basemodule.BaseActivity;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.ui.login.LoginActivity;

/**
 * 启动界面
 * 如果activity不需要加载progress  可以继承 {@link BaseActivity}
 * <p>
 * 不要使用setConventView()  使用style设置背景
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(Config.APPLICATIONID, Config.GETSN_DIR));
        startActivityForResult(intent, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String pan = data.getStringExtra("dsn");
            SpUtil.putString(this, Config.MACHINECODE, "POS_" + pan);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityIntent.readyGo(SplashActivity.this, LoginActivity.class);
                    finish();
                }
            }, 1000);
        }
    }
}
