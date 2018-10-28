package com.rflash.magiccube.ui.renewal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2018/10/14.
 * 续费
 */

public class RenewalActivity extends BaseActivity{

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);
        initView();
    }

    private void initView(){

    }

    @OnClick({R.id.title_back_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.title_back_tv:
                finish();
                break;
        }
    }
}
