package com.rflash.magiccube.ui.collection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rflash.basemodule.BaseActivity;
import com.rflash.magiccube.R;
import com.rflash.magiccube.ui.handswipecode.HandSwipeCodeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lys
 * @time 2018/10/8 17:02
 * @desc: 收款
 */

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.collection_rv)
    RecyclerView collection_rv;

    CollectionAdapter collectionAdapter;
    List<CollectionBean> collectionList = new ArrayList<>();

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initView();
    }

    private void initView() {

        collectionList.add(new CollectionBean(Color.parseColor("#EE0237"), "个人店铺收款", R.mipmap.icon_collection1));
        collectionList.add(new CollectionBean(Color.parseColor("#2CA347"), "微信收款", R.mipmap.icon_collection_wechart));
        collectionList.add(new CollectionBean(Color.parseColor("#1A81D0"), "支付宝收款", R.mipmap.icon_collection_alipay));
        collectionList.add(new CollectionBean(Color.parseColor("#F79D2F"), "财务报表（收款）", R.mipmap.icon_collection4));

        collectionAdapter = new CollectionAdapter(collectionList);
        collectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(CollectionActivity.this, HandSwipeCodeActivity.class);
                        startActivity(intent);
                        break;
                }


            }
        });
        collection_rv.setLayoutManager(new LinearLayoutManager(this));
        collection_rv.setAdapter(collectionAdapter);
    }

    @OnClick({R.id.title_back_tv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back_tv:
                finish();
                break;
        }
    }
}
