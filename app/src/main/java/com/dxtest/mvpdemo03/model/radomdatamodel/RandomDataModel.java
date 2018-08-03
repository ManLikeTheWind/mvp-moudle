package com.dxtest.mvpdemo03.model.radomdatamodel;

import android.util.Log;

import com.dxtest.mvpdemo03.base.BaseModel;
import com.dxtest.mvpdemo03.base.CallBack;
import com.dxtest.mvpdemo03.mvpeg02.thread.ThreadPoolSize5;

import java.util.Map;

/**
 * ....package: com.dxtest.mvpdemo03.model
 * <br>.author: dongxiang
 * <br>...date: 2018/5/3  9:53
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class RandomDataModel extends BaseModel<String> {

    @Override
    protected void requestGet(String url, CallBack<String> callback) {
        super.requestGet(url, callback);
    }

    @Override
    protected void requestPost(String url, Map params, CallBack<String> callback) {
        super.requestPost(url, params, callback);
    }
    @Override
    public void execute(CallBack<String> callback) {
        String sourceFilePath=mParams[0];
        String destFilePath=mParams[1];


        long currentPosition=Long.valueOf(mParams[2]);

        ThreadPoolSize5.get().executor(new RunnableCopyFile(sourceFilePath,destFilePath,currentPosition,callback));

//        for (int i=0;i<10;i++){
//            ThreadPoolSize5.get().executor(new RunnableTime(i));
//        }
    }

    class  RunnableTime implements Runnable{
        private int index;

        public RunnableTime(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            Log.e("RandomDataModel", Thread.currentThread()+"; run:  pre i = "+index);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("RandomDataModel", Thread.currentThread()+"; run:  afti = "+index);
        }
    }


}
