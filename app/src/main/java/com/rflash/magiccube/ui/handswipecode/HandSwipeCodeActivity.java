package com.rflash.magiccube.ui.handswipecode;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.db.DatabaseHelper;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.cardrecord.CardRecord;
import com.rflash.magiccube.ui.cardrecord.CardRecordActivity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 手工刷卡
 */

public class HandSwipeCodeActivity extends MVPBaseActivity<HandSwipeCodeContract.View, HandSwipeCodePresenter> implements HandSwipeCodeContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.edt_money)
    EditText edtMoney;

    private DatabaseHelper helper;


    private static final int SALE_REQUESTCODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_swipe_code);
        initView();
    }

    private void initView() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandSwipeCodeActivity.this.finish();
            }
        });

    }


    /**
     * 点击查看消费记录
     */

    @OnClick(R.id.tv_card_record)
    public void onCardRecord() {
        ActivityIntent.readyGo(this, CardRecordActivity.class);
    }


    /**
     * 确定金额
     */
    @OnClick(R.id.tv_sure)
    public void onSure() {
        String money = edtMoney.getText().toString();
        String fenMoney = StringUtil.getFen(money);
        if (!money.equals("")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(Config.APPLICATIONID, Config.SALE_DIR));
            intent.putExtra("amount", fenMoney);//表示消费金额 1.23 元
            intent.putExtra("mode", 2);
            startActivityForResult(intent, SALE_REQUESTCODE);
        } else {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_CANCELED://
                String reason = data.getStringExtra("reason");
                if (reason != null) {
                    Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
                }
                break;
            case Activity.RESULT_OK://消费成功
                if (requestCode == SALE_REQUESTCODE) {
                    edtMoney.setText("");
                    Toast.makeText(this, "消费成功", Toast.LENGTH_SHORT).show();
                    if (helper == null) {
                        helper = new DatabaseHelper(this);
                    }
                    String money = "";
                    String bankcard = "";
                    try {
                        money = data.getStringExtra("amount");
                        bankcard = data.getStringExtra("pan");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(money) || TextUtils.isEmpty(bankcard)) {
                        return;
                    }
                    CardRecord user = new CardRecord();
                    user.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    user.setBankcard(StringUtil.getBankNo(bankcard));
                    user.setMoney(money);
                    helper.insertAUser(user);
                }
                break;
        }
    }
}
