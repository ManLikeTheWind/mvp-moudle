/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.dxtest.mvpdemo03.mvpeg01;

import android.util.Log;

import com.dxtest.mvpdemo03.base.BasePresenter;
import com.dxtest.mvpdemo03.base.CallBack;
import com.dxtest.mvpdemo03.base.DataModel;
import com.dxtest.mvpdemo03.base.Token;
import com.dxtest.mvpdemo03.utils.DialogUtils;

/**
 * ....package: com.dxtest.mvpdemo03.mvpeg01
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  17:36
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class MvpPresenter01 extends BasePresenter<MvpView01> {
    public static final String TAG=MvpPresenter01.class.getSimpleName();
    public void getData(String...params){
        if (!isViewAttached()){
            return;
        }

        getMvpView().showLoading(DialogUtils.DIALOG_COMM_PROGRESS,null);
        DataModel
                .request(Token.MODEL_DATA_RANDOM)
                .params(params)
                .execute(new CallBack<String>(){
                    @Override
                    public void onProgressCallBack(long currentPosition, long total) {
                        Log.e(TAG, "CallBack: onSuccess currentPosition "+currentPosition+":total "+total);
                        if (!isViewAttached()){
                            return;
                        }

                    }

                    @Override
                    public void onSuccess(String data) {
                        Log.e(TAG, "CallBack: onSuccess "+data);
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showData(data);
                    }

                    @Override
                    public void onFailure(String data) {
                        Log.e(TAG, "CallBack: onFailure"+data);
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showData(data);
                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "CallBack: onError");
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showData("");
                        getMvpView().showToast("error-error-error",-1);

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "CallBack: onComplete");
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().dimissLoading(DialogUtils.DIALOG_COMM_PROGRESS);

                    }
                });


    }


}
