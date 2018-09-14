package com.jaygege.mvppractice;

import com.jaygege.mvppractice.base.presenter.AbstractPresenter;
import com.jaygege.mvppractice.base.view.AbstractView;

/**
 * 该场景下用到的View和Presenter
 * 之后可以定义SchoolContract，针对于SchoolActivity的
 * 也可以定义HomeContract，针对HomeActivity的
 */
public interface MvpContract {

    interface View extends AbstractView{
        void showData(String data);
    }

    interface Presenter extends AbstractPresenter<View>{
        void getData(String params);
    }
}
