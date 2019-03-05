package com.rflash.magiccube.ui.consumemodify;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rflash.basemodule.utils.StringUtil;
import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.presentoperation.OperationItem;
import com.rflash.magiccube.ui.presentoperation.consume.ConsumeFragment;
import com.rflash.magiccube.view.DefaultLoadCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 规划修改-消费
 */

public class ConsumeModifyActivity extends MVPBaseActivity<ConsumeModifyContract.View, ConsumeModifyPresenter> implements ConsumeModifyContract.View, AdapterView.OnItemSelectedListener, TextWatcher, ItemClick, LoadRecyclerView.OnLoadMoreListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.edt_money)
    EditText edtMoney;

    @BindView(R.id.spinner_channel)
    Spinner spinnerChannel;


    @BindView(R.id.edt_com)
    EditText edtCom;

    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;

    //修改RESULT_CODE
    public static final int MODIFY_RESULT_CODE = 0x33333;

    //需要修改的item 对象
    private OperationItem operationItem;

    // spinner 数据源
    private List<Channel.ResultBean> channelList;

    private SpinnerAdapter spinnerAdapter;

    //渠道号
    private String channel;

    private List<Merchant.ResultBean> merchantList;

    private MerchantAdapter merchantAdapter;


    private String merchantCode;

    private String planId;

    //输入框中的商户名
    private String merchantName="";

    private int pageNum = 1;
    private int mTotalPage;

    private boolean loadMore = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consum_modify);

        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "修改参数传递失败，重新操作", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            operationItem = intent.getParcelableExtra(ConsumeFragment.MODIFY_KEY);
            planId = operationItem.getPlanId();
        }

        if (operationItem == null) {
            Toast.makeText(this, "修改参数传递为空，重新操作", Toast.LENGTH_SHORT).show();
            finish();
        }

        edtCom.setText(operationItem.getMerchantName());
        edtMoney.setText(StringUtil.getTwoPointString(operationItem.getAmt()));
        channel = operationItem.getChannel();
        merchantCode = operationItem.getMerchantCode();

        mPresenter.queryChannel();
        channelList = new ArrayList<>();
        spinnerAdapter = new SpinnerAdapter(this, channelList);
        spinnerChannel.setAdapter(spinnerAdapter);
        spinnerChannel.setOnItemSelectedListener(this);

        merchantList = new ArrayList<>();
        merchantAdapter = new MerchantAdapter(this, merchantList);
        merchantAdapter.setItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setAdapter(merchantAdapter);

        edtCom.addTextChangedListener(this);

        toolbar.setNavigationOnClickListener(v -> ConsumeModifyActivity.this.finish());


    }


    /**
     * 修改
     */
    @OnClick(R.id.tv_modify)
    public void modify() {
        String amtYuan = edtMoney.getText().toString().trim();
        String amt = StringUtil.getFen(amtYuan);
        if (TextUtils.isEmpty(amt) || TextUtils.isEmpty(merchantCode) || TextUtils.isEmpty(channel)) {
            Toast.makeText(this, "请完善规划参数", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.modify(planId, amt, merchantCode, channel);
    }


    /**
     * 清空公司输入框
     */
    @OnClick(R.id.iv_dismiss)
    public void clearCom() {
        edtCom.setText("");
    }

    /**
     * 获取渠道
     */
    @Override
    public void getChannel(Channel c) {
        List<Channel.ResultBean> result = c.getResult();

        int totalNum = c.getTotalNum();
        if (totalNum > 0 && result != null) {
            Channel.ResultBean resultBean = null;
            for (int i = 0; i < result.size(); i++) {
                resultBean = result.get(i);
                if (channel.equals(resultBean.getChannel())) {
                    result.remove(resultBean);
                    break;
                }
            }
            result.add(0, resultBean);
            channelList.addAll(result);
        } else {
            Toast.makeText(this, "没有可用渠道", Toast.LENGTH_SHORT).show();
        }
        spinnerAdapter.notifyDataSetChanged();
    }


    /**
     * 获取商户名称
     */
    @Override
    public void getMerchant(Merchant merchant, String highLightText) {
        int totalNum = merchant.getTotalNum();
        mTotalPage = merchant.getTotalPage();
        List<Merchant.ResultBean> result = merchant.getResult();
        if (totalNum > 0 && result != null) {
            if (!loadMore) {
                merchantList.clear();
            }
            merchantList.addAll(result);
            merchantAdapter.setHighLightText(highLightText);
            merchantAdapter.notifyDataSetChanged();

        }
//        else {
//            Toast.makeText(this, "没有匹配的商户", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * 修改成功
     */
    @Override
    public void modifySuccess() {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        setResult(MODIFY_RESULT_CODE);
        finish();
    }


    /**
     * spinner item select
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        channel = channelList.get(position).getChannel();

        pageNum = 1;
        loadMore = false;
        mPresenter.queryMerchant(channel, merchantName, pageNum + "");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * EditText  内容变化监听
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (TextUtils.isEmpty(channel)) {
//            Toast.makeText(this, "渠道不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        pageNum = 1;
//        loadMore = false;
//        merchantName = s.toString();
//        if (TextUtils.isEmpty(merchantName)) {
//            Toast.makeText(this, "渠道商户不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        mPresenter.queryMerchant(channel, merchantName, pageNum + "");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 选中某个商户
     *
     * @param merchant
     */
    @Override
    public void selectItem(Merchant.ResultBean merchant) {
        edtCom.setText(merchant.getMerchantName());
        merchantCode = merchant.getMerchantCode();
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoad() {
        loadMore = true;
        if (mTotalPage >= pageNum + 1) {
            pageNum++;
            mPresenter.queryMerchant(channel, merchantName, pageNum + "");
        } else {
            recyclerView.onStopLoad();
            Toast.makeText(this, "没有更多数据啦", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
        recyclerView.onStopLoad();
    }
}
