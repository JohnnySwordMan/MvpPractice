package com.jaygege.mvppractice;

import com.jaygege.mvppractice.base.presenter.BasePresenter;

public class MvpPresenter extends BasePresenter<MvpContract.View> implements MvpContract.Presenter {
    @Override
    public void getData(String params) {

        if (!isViewAttached()) {
            return;
        }

        final MvpContract.View view = getView();
        view.showLoading();

        MvpModel.queryNetData(params, new MvpCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    view.showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    view.showData(msg);
                    view.showToast(msg);
                }
            }

            @Override
            public void onError(String msg) {
                if (isViewAttached()) {
                    view.showData(msg);
                    view.showError(msg);
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    view.hideLoading();
                }
            }
        });
    }

    @Override
    public void fun() {

    }
}
