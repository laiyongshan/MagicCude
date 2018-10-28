package com.rflash.magiccube.ui.loanhandle;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.credithandle.CardLink;
import com.rflash.magiccube.ui.credithandleweb.CreditHandleWebActivity;
import com.rflash.magiccube.util.ImgHelper;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;


/**
 *  贷款业务办理界面
 *  Created by Guobaihui on 2018/03/19.
 */

public class LoanHandleActivity extends MVPBaseActivity<LoanHandleContract.View, LoanHandlePresenter> implements LoanHandleContract.View, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ll_credit_handle)
    LinearLayout ll_credit_handle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_handle);
        initView();

    }

    private void initView() {
        toolbar.setTitle("贷款业务");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanHandleActivity.this.finish();
            }
        });
        mPresenter.queryLink(Config.IOAN);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void getHandleLink(CardLink cardLink) {
        List<CardLink.ResultBean> result = cardLink.getResult();
        if(result != null && result.size() > 0){
            for (int i =0; i<result.size(); i++) {
                CardLink.ResultBean resultBean = result.get(i);
                try {
                    TextView tv = new TextView(this);
                    if(StringUtil.isEmpty(resultBean.getIcon())){
                        tv.setBackgroundResource(R.mipmap.china_merchants_bank);
                    }else{
                        Bitmap bitmap = ImgHelper.bytes2Bimap( ImgHelper.decode(resultBean.getIcon())) ;
                        // 获得图片的宽高
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        // 设置想要的大小
                        int newWidth = 670;
                        int newHeight = 110;
                        // 计算缩放比例
                        float scaleWidth = ((float) newWidth) / width;
                        float scaleHeight = ((float) newHeight) / height;
                        // 取得想要缩放的matrix参数
                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleHeight);
                        // 得到新的图片
                        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
                        Drawable drawable = new BitmapDrawable(newbm);
                        tv.setBackground(drawable);
                    }
                    // 第一个参数为宽的设置，第二个参数为高的设置。
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(670, 110);
                    layoutParams.setMargins(30,30,30,0);//4个参数按顺序分别是左上右下
                    tv.setLayoutParams(layoutParams);
                    tv.setText(resultBean.getName()+"信用卡办理");
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setTag(resultBean);
                    ll_credit_handle.addView(tv,i);
                    tv.setOnClickListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View tv) {
        TextView textView = (TextView) tv;
        Bundle bundle = new Bundle();
        bundle.putSerializable("CardLink",(CardLink.ResultBean)textView.getTag());
        ActivityIntent.readyGo(this, CreditHandleWebActivity.class,bundle);
    }
}
