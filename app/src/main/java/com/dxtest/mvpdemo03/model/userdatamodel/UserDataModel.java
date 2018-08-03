/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.dxtest.mvpdemo03.model.userdatamodel;

import android.os.Handler;
import android.os.Message;

import com.dxtest.mvpdemo03.base.BaseModel;
import com.dxtest.mvpdemo03.base.CallBack;

/**
 * ....package: com.dxtest.mvpdemo03.model
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  17:28
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class UserDataModel extends BaseModel<String> {

    Handler handler=new Handler(/**Looper.getMainLooper(),*/ new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });

    @Override
    public void execute(final CallBack<String> callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mParams[0]){
                    case "normal":
                        callback.onSuccess("根据参数"+mParams[0]+"的请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("根据参数"+mParams[0]+"的请求网络数据失败");
                        break;
                    case "error":
                        callback.onError();
                        break;
                    default:
                        break;
                }
                callback.onComplete();
            }
        },2000);
    }
}
