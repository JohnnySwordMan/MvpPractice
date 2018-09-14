package com.jaygege.mvppractice.base.view;

/**
 * View基类
 * Created by geyan on 2018
 */
public interface AbstractView {

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void showToast(String msg);
}
