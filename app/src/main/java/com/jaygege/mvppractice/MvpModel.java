package com.jaygege.mvppractice;

import android.os.Handler;

/**
 * 数据层，进行网络请求
 */
public class MvpModel {
    /**
     * 利用handler的post方式模拟网络请求
     */
    public static void queryNetData(final String params, final MvpCallback<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (params) {
                    case "normal":
                        callback.onSuccess("根据参数 " + params + " 成功请求到网络数据");
                        break;
                    case "failure":
                        callback.onFailure("请求失败，参数不正确");
                        break;
                    case "error":
                        callback.onError("异常");
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }

}
