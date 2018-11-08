package com.rflash.magiccube.ui.shanghu;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rflash.basemodule.utils.ActivityIntent;
import com.rflash.magiccube.R;
import com.rflash.magiccube.event.PositionMessage;
import com.rflash.magiccube.http.BaseBean;
import com.rflash.magiccube.mvp.MVPBaseFragment;
import com.rflash.magiccube.ui.newmain.DirtData;
import com.rflash.magiccube.ui.shanghu.download.DownloadShanghuActivity;
import com.rflash.magiccube.util.TimerPikerTools;
import com.rflash.magiccube.util.ToolUtils;
import com.rflash.magiccube.view.SuccessProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * @author lys
 * @time 2018/10/11 14:11
 * @desc:
 */

public class ShangHuFragment extends MVPBaseFragment<ShanghuContract.View,ShanghuPresenter> implements BaseQuickAdapter.RequestLoadMoreListener, ShanghuContract.View, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.shanghu_drawerLayout)
    DrawerLayout shanghu_drawerLayout;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.shanghu_rv)
    RecyclerView shanghu_rv;

    @BindView(R.id.data_count_tv)
    TextView data_count_tv;

    @BindView(R.id.shanghu_option_tv)
    RoundTextView shanghu_option_tv;

    @BindView(R.id.download_shanghu_img)
    ImageView download_shanghu_img;

    @BindView(R.id.filtrate_img)
    ImageView filtrate_img;

    @BindView(R.id.option_rl)
    RelativeLayout option_rl;

    @BindView(R.id.all_selected_cb)
    CheckBox all_selected_cb;

    @BindView(R.id.selected_count_tv)
    TextView selected_count_tv;

    @BindView(R.id.delete_shanghu_rtv)
    RoundTextView delete_shanghu_rtv;

    //侧滑菜单里的控件
    @BindView(R.id.channelName_sp)
    MaterialSpinner channelName_sp;

    @BindView(R.id.merchantName_et)
    EditText merchantName_et;

    @BindView(R.id.merchantCode_et)
    EditText merchantCode_et;

    @BindView(R.id.state_sp)
    MaterialSpinner state_sp;

    @BindView(R.id.merchantType_sp)
    MaterialSpinner merchantType_sp;

    @BindView(R.id.startDate_tv)
    TextView startDate_tv;

    @BindView(R.id.endDate_tv)
    TextView endDate_tv;

    @BindView(R.id.clear_filter_tv)
    TextView clear_filter_tv;

    @BindView(R.id.sure_filter_tv)
    TextView sure_filter_tv;

    SuccessProgressDialog successProgressDialog;

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");


    String channelName;
    String merchantName;
    String state;
    String merchantCode;
    String merchantType;
    String startDate;
    String endDate;
    String bind="Y";
    int pageNum=1;

    boolean isOption;

    private int TOTAL_COUNTER ; //所有的数据总数

    DirtData dirtData;

    ShanghuBean shanghuBean;
    ShanghuAdapter shanghuAdapter;
    List<ShanghuBean.ResultBean> shanghuBeanList=new ArrayList<>();

    List<String> merchantList=new ArrayList<>();


    List<String> ChannelList=new ArrayList<>();
    List<String> MerchantsTypeList=new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.fragment_shanghu;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void initView() {

        dirtData=new DirtData(getActivity());

        refresh_layout.setColorSchemeColors(ToolUtils.Colors);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setRefreshing(true);

        successProgressDialog=new SuccessProgressDialog(getActivity());

        EventBus.getDefault().register(this);//注册

        ChannelList=dirtData.getChannelList();
        ChannelList.add(0,"请选择渠道名");
        channelName_sp.setItems(ChannelList);
        channelName_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i!=0){
                    channelName=ChannelList.get(i);
                }else{
                    channelName="";
                }
            }
        });

        state_sp.setItems(dirtData.ShanghuStateArr);
        state_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i==1){
                    state="vaild";
                }else if(i==2){
                    state="freeze";
                }else{
                    state="";
                }
            }
        });
        MerchantsTypeList=dirtData.getMerchantsTypeList();
        MerchantsTypeList.add(0,"请选择商户类型");
        merchantType_sp.setItems(MerchantsTypeList);
        merchantType_sp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(i!=0){
                    merchantType=MerchantsTypeList.get(i);
                }else{
                    merchantType="";
                }
            }
        });

        shanghu_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shanghuAdapter = new ShanghuAdapter(isOption, shanghuBeanList);
        shanghuAdapter.setOnLoadMoreListener(this,shanghu_rv);
        shanghuAdapter.disableLoadMoreIfNotFullPage();
        shanghu_rv.setAdapter(shanghuAdapter);



        all_selected_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                    selected_count_tv.setText(shanghuAdapter.getData().size()+"");
//                else
//                    selected_count_tv.setText("0");

                for(int i=0;i<shanghuBeanList.size();i++){
                    shanghuBeanList.get(i).setSelected(isChecked);
                }
                shanghuAdapter.selectAll();
            }
        });

        getMerchantData();//获取已下载的商户数据
    }

    @OnClick({R.id.shanghu_option_tv,R.id.download_shanghu_img,R.id.filtrate_img,R.id.delete_shanghu_rtv,
    R.id.clear_filter_tv,R.id.sure_filter_tv,R.id.startDate_tv,R.id.endDate_tv
    })
    public void click(View view){
        switch (view.getId()){
            case R.id.shanghu_option_tv:
                isOption=!isOption;
                if(isOption) {
                    option_rl.setVisibility(View.VISIBLE);
                    shanghu_option_tv.setText("完成");
                }else {
                    option_rl.setVisibility(View.GONE);
                    shanghu_option_tv.setText("操作");
                }
                shanghuAdapter = new ShanghuAdapter(isOption, shanghuBeanList);
                shanghuAdapter.setOnLoadMoreListener(this,shanghu_rv);
                shanghuAdapter.disableLoadMoreIfNotFullPage();
                shanghu_rv.setAdapter(shanghuAdapter);
                break;

            case R.id.download_shanghu_img://跳转商户下载页面
                ActivityIntent.readyGo(getActivity(),DownloadShanghuActivity.class);
                break;

            case R.id.filtrate_img:
                shanghu_drawerLayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.delete_shanghu_rtv://解绑商户
                final MaterialDialog mMaterialDialog = new MaterialDialog(context);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("确定删除当前数据？");
                mMaterialDialog.setPositiveButton("删除",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        merchantList.clear();
                        for (int i=0;i<shanghuBeanList.size();i++) {
                            if (shanghuBeanList.get(i).getSelected()) {
                                merchantList.add(shanghuBeanList.get(i).getChannel() + "|" + shanghuBeanList.get(i).getMerchantCode());
                            }
                        }
                        mPresenter.unbindShanghu(getMerchantParams(merchantList));
                    }
                });

                mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
                mMaterialDialog.show();
                break;

            case R.id.startDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择服务月份", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startDate = simpleDateFormat.format(date);
                        startDate_tv.setText(startDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.endDate_tv:
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择服务月份", true, true, false, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endDate = simpleDateFormat.format(date);
                        endDate_tv.setText(endDate + "");
                    }
                });
                mTimePikerView.show();
                break;

            case R.id.clear_filter_tv:
                state_sp.setSelectedIndex(0);
                merchantType_sp.setSelectedIndex(0);
                channelName_sp.setSelectedIndex(0);
                state="";
                merchantType="";
                channelName="";
                merchantName_et.setText("");
                merchantCode_et.setText("");
                startDate_tv.setText("");
                endDate_tv.setText("");
                break;

            case R.id.sure_filter_tv:
                pageNum=1;
                getMerchantData();
                shanghu_drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }


    private void getMerchantData(){
        merchantName=merchantName_et.getText().toString().trim();
        merchantCode=merchantCode_et.getText().toString().trim();
        mPresenter.queryShanghu(channelName,merchantName,state,merchantCode,merchantType,startDate,endDate,bind,pageNum+"");
    }

    @Override
    public void onRefresh() {
        pageNum=1;
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
        if(pageNum!=1) {
            //获取更多数据失败
            Toast.makeText(getActivity(), "获取更多数据失败", Toast.LENGTH_LONG).show();
            shanghuAdapter.loadMoreFail();
        }
    }

    @Override
    public void getDataSuccess(ShanghuBean response) {
        if(response!=null) {
            shanghuBean=response;
            TOTAL_COUNTER=shanghuBean.getTotalNum();
            data_count_tv.setText("共"+shanghuBean.getTotalNum()+"条数据");
            if(pageNum==1){
                shanghuBeanList=shanghuBean.getResult();
                shanghuAdapter.setNewData(shanghuBeanList);
            }else {
                shanghuBeanList.addAll(shanghuBean.getResult());
                shanghuAdapter.notifyDataSetChanged();
//                shanghuAdapter.addData(shanghuBean.getResult());
                shanghuAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void unbindLoading() {

    }

    @Override
    public void unbindError(String msg) {

    }

    @Override
    public void unbindSuccess() {
        successProgressDialog.showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                successProgressDialog.dismiss();
                pageNum=1;
                getMerchantData();
            }
        },1500);
        option_rl.setVisibility(View.GONE);
        shanghu_option_tv.setText("操作");
        shanghuAdapter = new ShanghuAdapter(false, shanghuBeanList);
        shanghuAdapter.disableLoadMoreIfNotFullPage();
        shanghu_rv.setAdapter(shanghuAdapter);
    }


    @Override
    public void unbindFinish() {

    }

    @Override
    public void onLoadMoreRequested() {
        refresh_layout.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (shanghuAdapter.getData().size() >= TOTAL_COUNTER) {
                    //数据全部加载完毕
                    shanghuAdapter.loadMoreEnd();
                } else {
                    //获取更多数据
                    pageNum++;
                    getMerchantData();
                }
                refresh_layout.setEnabled(true);
            }
        },1500);
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

    //ui主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(PositionMessage msg) {
        int i = 0;
        shanghuBeanList.get(msg.getPosition()).setSelected(msg.getChecked());
        for(ShanghuBean.ResultBean bean:shanghuBeanList){
            if(bean.getSelected())
                i++;
        }
        selected_count_tv.setText(i+"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除注册
    }
}
