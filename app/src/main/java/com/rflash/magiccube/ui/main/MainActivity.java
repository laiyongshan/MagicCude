package com.rflash.magiccube.ui.main;


import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.basemodule.updateApp.DownLoadApk;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.batchrepay.BatchRepayActivity;
import com.rflash.magiccube.ui.bill.BillActivity;
import com.rflash.magiccube.ui.credithandle.CreditHandleActivity;
import com.rflash.magiccube.ui.creditquery.CreditQueryActivity;
import com.rflash.magiccube.ui.handswipecode.HandSwipeCodeActivity;
import com.rflash.magiccube.ui.loanhandle.LoanHandleActivity;
import com.rflash.magiccube.ui.operationtoday.OperationTodayActivity;
import com.rflash.magiccube.ui.presentoperation.PresentOperationActivity;
import com.rflash.magiccube.ui.workreport.WorkReportActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 主界面
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {


    //用户名
    @BindView(R.id.tv_user)
    TextView tvUser;

    //账单确认上的数字
    @BindView(R.id.tv_num)
    TextView tvNum;

    private AlertDialog.Builder mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name = SpUtil.getString(this, Config.ACCOUNT, "");
        tvUser.setText(getResources().getString(R.string.main_user, name));
        removeOldApk(this, "magiccube");
        mPresenter.updateApp();
        mPresenter.getBillCount();

    }


    @OnClick({R.id.tv_bill, R.id.tv_operation_today,
            R.id.tv_batch_repay, R.id.tv_hand_swipe_code,
            R.id.tv_work_report, R.id.tv_handle,
            R.id.tv_score, R.id.tv_add_service,
            R.id.tv_business_school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bill://账单确认
                tvNum.setVisibility(View.GONE);
                ActivityIntent.readyGo(this, BillActivity.class);
                break;
            case R.id.tv_operation_today://今日操作
                ActivityIntent.readyGo(this, PresentOperationActivity.class);
                break;
            case R.id.tv_batch_repay://批量还款
                ActivityIntent.readyGo(this, BatchRepayActivity.class);
                break;
            case R.id.tv_hand_swipe_code://手工刷卡
                ActivityIntent.readyGo(this, HandSwipeCodeActivity.class);
                break;
            case R.id.tv_work_report://工作报表
                ActivityIntent.readyGo(this, WorkReportActivity.class);
                break;
            case R.id.tv_handle://信用卡办理
                ActivityIntent.readyGo(this, CreditHandleActivity.class);
                break;
            case R.id.tv_score://信用查询
                ActivityIntent.readyGo(this, CreditQueryActivity.class);
                break;
            case R.id.tv_add_service://贷款业务
                ActivityIntent.readyGo(this, LoanHandleActivity.class);
                break;
            case R.id.tv_business_school:
                Toast.makeText(this, "请稍等，功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
                
        }
    }


    /**
     * 弹框 更新
     *
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
            DownLoadApk.download(MainActivity.this, downUrl, title, description, appName);
        }).setCancelable(false).show();
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
     * 获取首页消息数量
     *
     * @param billCount
     */
    @Override
    public void getBillCount(BillCount billCount) {
        BillCount.ResultBean result = billCount.getResult();
        if (result != null) {
            int billConfirm = result.getBILL_CONFIRM();
            if (billConfirm <= 0) {
                tvNum.setVisibility(View.INVISIBLE);
            } else {
                tvNum.setVisibility(View.VISIBLE);
                tvNum.setText(billConfirm + "");
            }
        }
    }

}
