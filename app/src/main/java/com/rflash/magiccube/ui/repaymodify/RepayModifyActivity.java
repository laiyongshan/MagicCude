package com.rflash.magiccube.ui.repaymodify;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.repay.RepayFragment;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 还款修改
 */

public class RepayModifyActivity extends MVPBaseActivity<RepayModifyContract.View, RepayModifyPresenter> implements RepayModifyContract.View {



    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.edt_money)
    EditText edtMoney;

    //需要修改的item 对象
    private OperationItem operationItem;

    //修改RESULT_CODE
    public static final int MODIFY_RESULT_CODE = 0x44444;

    private String planId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_modify);


        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "修改参数传递失败，重新操作", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            operationItem = intent.getParcelableExtra(RepayFragment.MODIFY_KEY);
            planId = operationItem.getPlanId();
        }

        if (operationItem == null) {
            Toast.makeText(this, "修改参数传递为空，重新操作", Toast.LENGTH_SHORT).show();
            finish();
        }

        edtMoney.setText(StringUtil.getTwoPointString(operationItem.getAmt()));
        toolbar.setNavigationOnClickListener(v -> RepayModifyActivity.this.finish());

    }

    /**
     * 修改
     */
    @OnClick(R.id.tv_modify)
    public void modify() {
        String amtYuan = edtMoney.getText().toString().trim();
        String amt = StringUtil.getFen(amtYuan);
        if (TextUtils.isEmpty(amt) ) {
            Toast.makeText(this, "请完善规划参数", Toast.LENGTH_SHORT).show();
            return;
        }

        mPresenter.modify(planId, amt);

    }

    @Override
    public void modifySuccess() {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        setResult(MODIFY_RESULT_CODE);
        finish();
    }
}
