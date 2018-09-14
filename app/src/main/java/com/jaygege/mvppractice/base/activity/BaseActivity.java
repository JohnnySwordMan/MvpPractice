package com.jaygege.mvppractice.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jaygege.mvppractice.base.presenter.AbstractPresenter;
import com.jaygege.mvppractice.base.view.AbstractView;

/**
 * 专为mvp形式的Activity创建的基类，Fragment同Activity，
 * 如果在代码中还存在不需要使用mvp的其他Activity，可以继承AbstractSimpleActivity
 * @param <T>
 */
public abstract class BaseActivity<T extends AbstractPresenter> extends AbstractSimpleActivity {


    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((AbstractView) this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    protected abstract T createPresenter();
}
