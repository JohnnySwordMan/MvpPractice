package com.jaygege.mvppractice.base.presenter;

import com.jaygege.mvppractice.base.view.AbstractView;

/**
 * Presenter基类
 * Created by geyan on 2018
 */
public interface AbstractPresenter<T extends AbstractView> {

    public void attachView(T view);

    public void detachView();

    public T getView();

    public boolean isViewAttached();

    // 其他方法
    void fun();
}
