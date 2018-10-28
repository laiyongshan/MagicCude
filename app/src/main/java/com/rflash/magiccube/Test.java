package com.rflash.magiccube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rflash.basemodule.adapter.HeaderAndFooterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfan on 2017/11/18.
 * <p>
 * 删除理由：这是个测试Activity
 */
@Deprecated
public class Test extends AppCompatActivity {

//    CircleGraphView view;

    private TestAdapter adapter;

    private List<TestBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TestBean operationToday = null;
        for (int i = 0; i < 10; i++) {
            operationToday = new TestBean();
            operationToday.setBank("中国农业银行");
            operationToday.setBankCard("666666****1111");
            operationToday.setDate("2017-11-11 11:11");
            operationToday.setName("rflash");
            operationToday.setMoney("111");
            operationToday.setCardNum(i + "");
            operationToday.setOperationDate("2017-11-11 11:" + i);
            list.add(operationToday);
        }

        adapter = new TestAdapter(this, list, R.layout.layout_operation_repay);
        HeaderAndFooterAdapter hAdapter = new HeaderAndFooterAdapter(adapter);
        RecyclerView recycleview = findViewById(R.id.recyclerView);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(hAdapter);
    }
}
