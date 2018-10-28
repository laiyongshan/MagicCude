package com.rflash.magiccube.ui.presentoperation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.presentoperation.consume.ConsumeFragment;
import com.rflash.magiccube.ui.presentoperation.repay.RepayFragment;
import com.rflash.magiccube.view.NormalDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 今日操作
 */

public class PresentOperationActivity extends MVPBaseActivity<PresentOperationContract.View, PresentOperationPresenter> implements PresentOperationContract.View, ViewPager.OnPageChangeListener {


    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_back)
    TextView tvBack;

    private List<Fragment> mFragmentList = new ArrayList<>();

    private ConsumeFragment mConsumeFragment;

    private RepayFragment mRepayFragment;
    private OperationPageAdapter mPageAdapter;


    //消费界面只显示这张卡
    private boolean consumeShowOnlyBank = false;

    //还款界面只显示这张卡
    private boolean repayShowOnlyBank = false;

    //消费界面 toolbar title
    private String consumeTitle;


    //还款界面 toolbar title
    private String repayTitle;

    public static final String DEFAULT_TITLE = "今日操作";

    //搜索dialog
    private NormalDialog searchDialog;


    @OnClick(R.id.tv_title)
    public void finishActivity() {
        finish();
    }

    @OnClick(R.id.tv_back)
    public void back() {
        resetToolbar();
    }


    @OnClick(R.id.iv_search)
    public void search() {
        showSearchDialog();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_pressent);
        tvTitle.setText(DEFAULT_TITLE);

        mConsumeFragment = ConsumeFragment.newInstance();
        mRepayFragment = RepayFragment.newInstance();

        mFragmentList.add(mConsumeFragment);
        mFragmentList.add(mRepayFragment);

        mPageAdapter = new OperationPageAdapter(getSupportFragmentManager(), mFragmentList);
        viewpager.setAdapter(mPageAdapter);
        tab.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(this);
    }


    /**
     * 显示搜索dialog
     */
    private void showSearchDialog() {
        if (searchDialog == null) {
            searchDialog = new NormalDialog(this, R.style.bottom_dialog, R.layout.dialog_search);
        }
        searchDialog.setGravity(Gravity.TOP);
        searchDialog.show();

        EditText edBankCard = searchDialog.findViewById(R.id.edt_bankcard);
        CheckBox cbOperation = searchDialog.findViewById(R.id.cb_operation);
        CheckBox cbUnOperation = searchDialog.findViewById(R.id.cb_un_operation);
        TextView tvReset = searchDialog.findViewById(R.id.tv_reset);
        TextView tvSearch = searchDialog.findViewById(R.id.tv_search);

        //初始化
        cbOperation.setChecked(false);
        cbUnOperation.setChecked(false);
        edBankCard.setText("");

        //重置
        tvReset.setOnClickListener(v -> {
            cbOperation.setChecked(false);
            cbUnOperation.setChecked(false);
            edBankCard.setText("");
        });

        //搜索
        tvSearch.setOnClickListener(v -> {
            int currentPage = viewpager.getCurrentItem();
            String bankCard = edBankCard.getText().toString();

            if (currentPage == 0) {//消费
                mConsumeFragment.CARDNO = bankCard;
                String state = "";
                if (!cbOperation.isChecked() && !cbUnOperation.isChecked()) {
                    state = Config.DEAL + "," + Config.NOT_OPERATOR;
                } else {
                    state = (cbOperation.isChecked() ? Config.DEAL : "") + "," + (cbUnOperation.isChecked() ? Config.NOT_OPERATOR : "");
                }
                if (state.startsWith(",")) {
                    state = state.replace(",", "");
                }
                mConsumeFragment.STATE = state;
                mConsumeFragment.search();
            } else if (currentPage == 1) {//还款
                mRepayFragment.CARDNO = bankCard;
                String state = "";
                if (!cbOperation.isChecked() && !cbUnOperation.isChecked()) {
                    state = Config.DEAL + "," + Config.NOT_OPERATOR;
                } else {
                    state = (cbOperation.isChecked() ? Config.DEAL : "") + "," + (cbUnOperation.isChecked() ? Config.NOT_OPERATOR : "");
                }
                if (state.startsWith(",")) {
                    state = state.replace(",", "");
                }
                mRepayFragment.STATE = state;
                mRepayFragment.search();
            }

            selectTheCard(bankCard);
            searchDialog.dismiss();

        });

    }


    /**
     * 点击返回重置
     */
    private void resetToolbar() {

        int currentItem = viewpager.getCurrentItem();
        if (currentItem == 0) {//消费
            consumeShowOnlyBank = false;
            consumeTitle = DEFAULT_TITLE;
            changeToolbar(consumeShowOnlyBank, consumeTitle);

            mConsumeFragment.CARDNO = "";
            mConsumeFragment.STATE = Config.NOT_OPERATOR + "," + Config.DEAL;
            mConsumeFragment.onRefresh();
        } else if (currentItem == 1) {//还款

            repayShowOnlyBank = false;
            repayTitle = DEFAULT_TITLE;
            changeToolbar(repayShowOnlyBank, repayTitle);

            mRepayFragment.CARDNO = "";
            mRepayFragment.STATE = Config.NOT_OPERATOR + "," + Config.DEAL;
            mRepayFragment.onRefresh();
        }
    }


    /**
     * 选中这张卡
     *
     * @param title toolbar 标题
     */
    public void selectTheCard(String title) {
        int currentPage = viewpager.getCurrentItem();
        if (currentPage == 0) {//消费
            if (!TextUtils.isEmpty(title)) {
                consumeShowOnlyBank = true;
                consumeTitle = DEFAULT_TITLE + "（银行卡号：" + title + "）";
            } else {
                consumeShowOnlyBank = false;
                consumeTitle = DEFAULT_TITLE;
            }

            changeToolbar(consumeShowOnlyBank, consumeTitle);

        } else if (currentPage == 1) {//还款
            if (!TextUtils.isEmpty(title)) {
                repayShowOnlyBank = true;
                repayTitle = DEFAULT_TITLE + "（银行卡号：" + title + "）";
            } else {
                repayShowOnlyBank = false;
                repayTitle = DEFAULT_TITLE;
            }

            changeToolbar(repayShowOnlyBank, repayTitle);

        }
    }


    /**
     * 改变toolbar
     *
     * @param title
     */
    private void changeToolbar(boolean show, String title) {
        if (show) {
            tvTitle.setText(title);
            tvBack.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText(DEFAULT_TITLE);
            tvBack.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * page改变时
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (position == 0) {//消费
            changeToolbar(consumeShowOnlyBank, consumeTitle);
        } else if (position == 1) {//还款
            changeToolbar(repayShowOnlyBank, repayTitle);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (searchDialog != null) {
            if (searchDialog.isShowing()) {
                searchDialog.dismiss();
            }
            searchDialog = null;
        }
    }
}
