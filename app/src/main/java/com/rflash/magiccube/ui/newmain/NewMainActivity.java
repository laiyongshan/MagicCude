package com.rflash.magiccube.ui.newmain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardManagerFragment;
import com.rflash.magiccube.ui.finance.FinanceManagerFragment;
import com.rflash.magiccube.ui.merchants.MerchantsFragment;
import com.rflash.magiccube.ui.other.OtherBusinessFragment;
import com.rflash.magiccube.ui.shanghu.ShangHuFragment;
import com.rflash.magiccube.view.TabBar;

public class NewMainActivity extends BaseActivity {


    TabBar mTabBar;
    int mFragmentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_new_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(Color.BLACK);
        }

        initView();
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initView() {
        Intent intent = getIntent();

//        if (null != intent) {
//            Bundle bundle = getIntent().getExtras();
//            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//        }


        mTabBar = (TabBar) findViewById(R.id.main_tabView);
        mTabBar.mSelectTextColor = getResources().getColor(R.color.colorPrimary);
        mTabBar.mTabBarViewBackgroundColor=Color.parseColor("#FFFFFF");
        mTabBar.mTextVTitleArray = new String[]{"首页","商户管理", "卡片管理", "财务管理", "其他业务"};
        mTabBar.mSelectImageVIconArray = new int[]{R.mipmap.icon_home_s, R.mipmap.icon_merchants_s,R.mipmap.icon_card_s, R.mipmap.icon_financial_s, R.mipmap.icon_other_s};
        mTabBar.mUnSelectImageVIconArray = new int[]{R.mipmap.icon_home_u,R.mipmap.icon_merchants_u, R.mipmap.icon_card_u, R.mipmap.icon_financial_u, R.mipmap.icon_other_u};
        mTabBar.mTabFragmentClassArray = new Class<?>[]{MerchantsFragment.class, ShangHuFragment.class, CardManagerFragment.class, FinanceManagerFragment.class, OtherBusinessFragment.class};
        mTabBar.mDefaultSelectIndex = mFragmentPage;
        mTabBar.refreshTabBar();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出时的时间
    private long mExitTime;
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2500) {
            Toast.makeText(NewMainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
