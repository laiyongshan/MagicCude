package com.rflash.magiccube.ui.cardrecord;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.rflash.basemodule.view.LoadRecyclerView;
import com.rflash.magiccube.R;
import com.rflash.magiccube.db.DatabaseHelper;
import com.rflash.magiccube.mvp.MVPBaseActivity;
import com.rflash.magiccube.ui.planningquery.PlanningQueryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 刷卡记录
 */

public class CardRecordActivity extends MVPBaseActivity<CardRecordContract.View, CardRecordPresenter> implements CardRecordContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.recyclerView)
    LoadRecyclerView recyclerView;


    @BindView(R.id.iv_nodata)
    ImageView ivNoData;

    private DatabaseHelper helper;


    private CardRecordAdapter adapter;

    private List<CardRecord> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_record);
        createDate();
        initView();
    }

    private void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardRecordActivity.this.finish();
            }
        });
        adapter = new CardRecordAdapter(this, list, R.layout.layout_card_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addEmptyView(ivNoData);
        recyclerView.setAdapter(adapter);
    }

    private void createDate() {
        showProgress();
        Observable.create(new ObservableOnSubscribe<List<CardRecord>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CardRecord>> e) throws Exception {
                if (helper == null) {
                    helper = new DatabaseHelper(CardRecordActivity.this);
                }
                List<CardRecord> cardRecords = helper.readAllUser();
                e.onNext(cardRecords);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CardRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CardRecord> cardRecords) {
                        dismissProgress();
                        list.clear();
                        list.addAll(cardRecords);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgress();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        dismissProgress();
                    }
                });
    }
}
