package com.rflash.magiccube.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rflash.basemodule.BaseActivity;

import java.lang.reflect.ParameterizedType;


/**
 * Activity基类
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends BaseActivity implements BaseView {
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }




    /**
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public <T> T getInstance(Object o, int i) {
        try {
            //getGenericSuperclass  获取当前运行类泛型父类类型，即为参数化类型
            //getActualTypeArguments  获取这个泛型的数组
            //newInstance   通过反射创建这个泛型对象
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
