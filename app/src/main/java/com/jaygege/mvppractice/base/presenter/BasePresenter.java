package com.jaygege.mvppractice.base.presenter;

import com.jaygege.mvppractice.base.view.AbstractView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 因为Presenter经常需要执行耗时操作，例如网络请求，
 * 如果Presenter持有Activity/Fragment的强引用，在网络请求结束之前Activity/Fragment被销毁，
 * 由于网络请求还未返回，因此Presenter一直持有Activity的引用，因此Activity对象无法被回收，发生内存泄漏。
 *
 * 解决方法：通过弱引用和Activity/Fragment的生命周期解决
 *
 * Created by geyan on 2018
 * @param <T>
 */
public abstract class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {

    protected Reference<T> mViewRef;

    /**
     * AbstractView的子类作为入参，由于Activity/Fragment会实现AbstractView子类接口，
     * 因此就将Presenter与Activity通过弱引用关联起来了
     * 建立关联
     * @param view
     */
    @Override
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 解除关联
     */
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 获取view
     */
    @Override
    public T getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否与View建立了关联，为了代码的健壮性，在Presenter中调用view方法，执行UI逻辑之前需要判断
     * @return
     */
    @Override
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
