package com.rflash.magiccube.ui.shanghu.download;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.PositionMessage;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/11 15:02
 * @desc:
 */

public class DownloadShanghuActivity extends MVPBaseActivity<DownloadContrat.View, DownloadPresenter> implements BaseQuickAdapter.RequestLoadMoreListener, DownloadContrat.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.download_drawerLayout)
    DrawerLayout download_drawerLayout;

    @BindView(R.id.title_back_tv)
    TextView title_back_tv;

    @BindView(R.id.data_count_tv)
    TextView data_count_tv;

    @BindView(R.id.download_option_tv)
    RoundTextView download_option_tv;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.download_rv)
    RecyclerView download_rv;

    @BindView(R.id.option_rl)
    RelativeLayout option_rl;

    @BindView(R.id.all_selected_cb)
    CheckBox all_selected_cb;

    @BindView(R.id.selected_count_tv)
    TextView selected_count_tv;

    @BindView(R.id.download_shanghu_rtv)
    RoundTextView download_shanghu_rtv;

    private int TOTAL_COUNTER; //所有的数据总数

    DownloadAdapter downloadAdapter;
    List<DownloadBean.ResultBean> downloadBeanList = new ArrayList<>();

    String channelName = "";
    String merchantType = "";
    String startDate = "";
    String endDate = "";
    String bind = "N";
    int pageNum;

    List<String> merchantList=new ArrayList<>();

    boolean isOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_shanghu);

        EventBus.getDefault().register(this);//注册

        initView();

        getMerchantData();
    }

    private void initView() {

        all_selected_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                downloadAdapter.selectAll();
                if (isChecked)
                    selected_count_tv.setText(downloadAdapter.getData().size() + "");
                else
                    selected_count_tv.setText("0");
            }
        });

        download_rv.setLayoutManager(new LinearLayoutManager(this));
        downloadAdapter = new DownloadAdapter(isOption, downloadBeanList);
        downloadAdapter.setOnLoadMoreListener(this, download_rv);
        downloadAdapter.disableLoadMoreIfNotFullPage();
        download_rv.setAdapter(downloadAdapter);
    }

    @OnClick({R.id.title_back_tv, R.id.filtrate_img, R.id.download_option_tv, R.id.download_shanghu_rtv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;

            case R.id.filtrate_img:
                download_drawerLayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.download_option_tv:
                isOption = !isOption;
                if (isOption) {
                    download_option_tv.setText("完成");
                    downloadAdapter = new DownloadAdapter(isOption, downloadBeanList);
                    download_rv.setAdapter(downloadAdapter);
                    option_rl.setVisibility(View.VISIBLE);
                } else {
                    download_option_tv.setText("下载");
                    downloadAdapter = new DownloadAdapter(isOption, downloadBeanList);
                    download_rv.setAdapter(downloadAdapter);
                    option_rl.setVisibility(View.GONE);
                }
                break;

            case R.id.download_shanghu_rtv://下载商户
                merchantList.clear();
                for (Map.Entry<Integer, Boolean> entry : downloadAdapter.map.entrySet()) {
                    if(entry.getValue()){
                        merchantList.add(downloadBeanList.get(entry.getKey()).getChannel()+"|"+downloadBeanList.get(entry.getKey()).getMerchantCode());
                    }
                }
                mPresenter.bindShanghu(getMerchantParams(merchantList));
                break;
        }
    }

    private void getMerchantData() {
        mPresenter.queryShanghu(channelName, "", "", "", merchantType, startDate, endDate, bind, pageNum + "");
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        getMerchantData();
    }

    @Override
    public void showRefresh() {
        refresh_layout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
        if (pageNum != 1) {
            //获取更多数据失败
            downloadAdapter.loadMoreFail();
        }
    }

    @Override
    public void bindFail(String msg) {

    }

    @Override
    public void bindSuccess(BaseBean response) {

    }

    @Override
    public void getDataSuccess(DownloadBean response) {
        if (response != null) {
            TOTAL_COUNTER = response.getTotalNum();
            data_count_tv.setText("共" + response.getTotalNum() + "条数据");
            if (pageNum == 1) {
                downloadBeanList = response.getResult();
                downloadAdapter.setNewData(downloadBeanList);
            } else {
                downloadBeanList.addAll(response.getResult());
                downloadAdapter.addData(response.getResult());
                downloadAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (downloadAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    downloadAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    getMerchantData();
                }
                refresh_layout.setEnabled(true);
            }
        }, 1500);
    }

    private String getMerchantParams(List<String> list){
        StringBuffer sbf=new StringBuffer();
        for(int i=0;i<list.size();i++){
            if(i==0){
                sbf.append(list.get(i)+"");
            }else{
                sbf.append(","+list.get(i));
            }
        }
        return sbf.toString();
    }

    int selectedCount;
    //ui主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(PositionMessage msg) {
        if (msg.getChecked())
            ++selectedCount;
        else
            --selectedCount;

        selected_count_tv.setText(selectedCount + "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除注册
    }
}
