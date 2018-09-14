package com.jaygege.mvppractice;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygege.mvppractice.base.activity.BaseActivity;
import com.jaygege.mvppractice.base.presenter.BasePresenter;

public class MainActivity extends BaseActivity<MvpPresenter> implements MvpContract.View,View.OnClickListener {

    private TextView mTvShow;
    private Button mBtnGetDataSuccess;
    private Button mBtnGetDataFail;
    private Button mBtnGetDataError;
    private ProgressDialog progressDialog;
    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvShow = findViewById(R.id.tv_show);
        mBtnGetDataSuccess = findViewById(R.id.btn_get_data_success);
        mBtnGetDataFail = findViewById(R.id.btn_get_data_fail);
        mBtnGetDataError = findViewById(R.id.btn_get_data_error);

        mBtnGetDataSuccess.setOnClickListener(this);
        mBtnGetDataFail.setOnClickListener(this);
        mBtnGetDataError.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    protected MvpPresenter createPresenter() {
        mvpPresenter = new MvpPresenter();
        return mvpPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_data_success:
                mvpPresenter.getData("normal");
                break;
            case R.id.btn_get_data_fail:
                mvpPresenter.getData("failure");
                break;
            case R.id.btn_get_data_error:
                mvpPresenter.getData("error");
                break;
        }
    }

    @Override
    public void showData(String data) {
        mTvShow.setText(data);
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
