package com.jaygege.mvppractice;

/**
 * Presenter中不直接进行网络请求，而是将这一操作交给model层，model通过回调，将数据传给Presenter，
 * Presenter就只处理服务端返回的数据、以及调用视图view的方法，进行UI操作
 *
 * 使用回调，能够减少Presenter与model层的耦合
 * Created by geyan on 2018
 * @param <T>
 */
public interface MvpCallback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError(String msg);

    void onComplete();
}
