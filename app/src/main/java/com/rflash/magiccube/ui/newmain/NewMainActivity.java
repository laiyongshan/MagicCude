package com.rflash.magiccube.ui.newmain;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.rflash.basemodule.updateApp.DownLoadApk;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardmanager.CardManagerFragment;
import com.rflash.magiccube.ui.finance.FinanceManagerFragment;
import com.rflash.magiccube.ui.main.AppInfo;
import com.rflash.magiccube.ui.merchants.MerchantsFragment;
import com.rflash.magiccube.ui.other.OtherBusinessFragment;
import com.rflash.magiccube.ui.salesmen.SalesmenBean;
import com.rflash.magiccube.ui.shanghu.ShangHuFragment;
import com.rflash.magiccube.util.CatchManager;
import com.rflash.magiccube.view.TabBar;

import java.io.File;
import java.util.List;

public class NewMainActivity extends MVPBaseActivity<NewMainContract.View,NewMainPresenter> implements NewMainContract.View{


    TabBar mTabBar;
    int mFragmentPage = 0;

    private AlertDialog.Builder mDialog;

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

        removeOldApk(this, "magiccube");
        mPresenter.updateApp();

        initView();
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initView() {
//        Intent intent = getIntent();
        mPresenter.queryDirt();//获取字典
        mPresenter.querySalesMan("","");
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
        if ((System.currentTimeMillis() - mExitTime) > 2500){
            Toast.makeText(NewMainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
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
    public void queryDirtSuccess(DirtBean response) {
        if (response!=null){
            CatchManager.putData2Cache(NewMainActivity.this,DirtData.key1, response.getResult());

        }
    }

    //查询业务员成功
    @Override
    public void querySalMenSuccess(SalesmenBean salesmenBean) {
        if(salesmenBean!=null){
            CatchManager.putData2Cache(NewMainActivity.this,DirtData.SALEMEN_KEY, salesmenBean.getResult());
            List<SalesmenBean.ResultBean> list = (List<SalesmenBean.ResultBean>) CatchManager.getCatchData(NewMainActivity.this, DirtData.SALEMEN_KEY);
            for(SalesmenBean.ResultBean resultBean:salesmenBean.getResult()){
                Log.i("DefaultObserver",list.size()+"");
            }
        }
    }

    /**
     * 每次进app时都去检测  更新目录中是否有旧版本的apk，有就删除
     *
     * @param context
     * @param appName
     */
    public void removeOldApk(Context context, String appName) {
        Uri uri = Uri.withAppendedPath(Uri.fromFile(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)), appName + ".apk");
        String path = uri.getPath();
        Log.i("---", path);
//       ///storage/emulated/0/Android/data/com.rflash.magiccube/files/Download/magiccube.apk
        //storage/emulated/0/Android/data/your-package/files/Download/magiccube.apk
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                if (!DownLoadApk.compare(DownLoadApk.getApkInfo(context, path), context)) {
                    file.delete();
                } else {
                    Log.i("---", "此路径存放的是最新的apk");
                }
            } else {
                Log.i("---", "此路径不存在apk");
            }
        }
    }

    /**
     * 检测更新接口成功回调
     */
    @Override
    public void queryUpdateSuccess(AppInfo info) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //如果后台返回的apkversion 更大 就更新
            if (info.getAppVersion() > packageInfo.versionCode) {
                forceUpdate(this, Config.APP_NAME, info.getAppUrl(), this.getString(R.string.app_update_name), info.getAppContent());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹框 更新
     * @param context
     * @param appName     这个是要存apk文件的路径需要的  比如：magiccube
     * @param downUrl
     * @param title
     * @param description
     */
    private void forceUpdate(final Context context, final String appName, final String downUrl, final String title, final String description) {
        mDialog = new AlertDialog.Builder(context);
        mDialog.setTitle(title);
        mDialog.setMessage(description);
        mDialog.setPositiveButton("立即更新", (dialog, which) -> {
            dialog.dismiss();
            DownLoadApk.download(NewMainActivity.this, downUrl, title, description, appName);
        }).setCancelable(false).show();
    }
}
