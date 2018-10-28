package com.rflash.magiccube.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.util.SignUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangfan on 2017/10/31.
 */

public class RetrofitFactory {

    public static final String TAG = "RetrofitFactory";


    /**
     * 转移到BaseActivity
     */
//    public static <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycle) {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream.subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(lifecycle);
//            }
//        };
//    }

    private ApiService apiService;


    private RetrofitFactory() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加拦截器
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i(TAG,message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(ApiService.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiService.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    private static class SingletonHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    public static ApiService getApiService() {
        return SingletonHolder.INSTANCE.apiService;
    }
}
