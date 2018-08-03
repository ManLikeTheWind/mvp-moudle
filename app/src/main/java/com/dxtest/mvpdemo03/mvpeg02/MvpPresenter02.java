package com.dxtest.mvpdemo03.mvpeg02;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.dxtest.mvpdemo03.base.BasePresenter;
import com.dxtest.mvpdemo03.base.CallBack;
import com.dxtest.mvpdemo03.base.DataModel;
import com.dxtest.mvpdemo03.base.Token;
import com.dxtest.mvpdemo03.utils.DialogUtils;

import java.util.List;


/**
 * ....package: com.dxtest.mvpdemo03.mvpeg02
 * <br>.author: dongxiang
 * <br>...date: 2018/5/3  9:43
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class MvpPresenter02 extends BasePresenter<MvpView02> {
    public static final String TAG=MvpPresenter02.class.getSimpleName();

    public void getData(String sourceFilePath,String destFilePath,long position){

        if (!isViewAttached()){
            return;
        }
        getMvpView().showLoading(DialogUtils.DIALOG_PROGRESS,null);
        DataModel.request(Token.MODEL_DATA_RANDOM)
                .params(new String[]{sourceFilePath,destFilePath,position+""})
                .execute(new CallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e(TAG,Thread.currentThread()+" ;onSuccess: data = "+data );
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showToast(data,-1);

                    }
                    @Override
                    public void onProgressCallBack(long currentPosition, long total) {
                        Log.e(TAG, Thread.currentThread()+" ;onProgressCallBack: currentPosition = "+currentPosition+";total = "+total );
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().onProgress(currentPosition,total);

                    }

                    @Override
                    public void onFailure(String data) {
                        Log.e(TAG, "onFailure: data = "+data );
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showToast(data,-1);

                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "onError: " );
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().showToast(Thread.currentThread()+" ;onError",-1);


                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, Thread.currentThread()+" ;onComplete: " );
                        if (!isViewAttached()){
                            return;
                        }
                        getMvpView().dimissLoading(DialogUtils.DIALOG_PROGRESS);

                    }

                });



    }


    /**
     * @param activity
     * @param forresultType {@link PkgConst#START_ACTIVITY_FOR_RESULT_PATH_CATEGORY} 和 {@link PkgConst#START_ACTIVITY_FOR_RESULT_PATH_CATEGORY_FILE}
     * @param requestCode
     */
    public void startActivityForResult(Activity activity,int forresultType,int requestCode){
        Intent intent=new Intent();
        switch (forresultType){
            case PkgConst.START_ACTIVITY_FOR_RESULT_PATH_CATEGORY:

                PackageManager packageManager = activity.getPackageManager();
                Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(mainIntent, 0);
                for (ResolveInfo resolveInfo:resolveInfos){
                    String packageName = resolveInfo.activityInfo.packageName;
                    if (packageName.equalsIgnoreCase("com.android.fileexplorer")){
                        String name = resolveInfo.activityInfo.name;
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        return;
                    }
                }
                break;
            case PkgConst.START_ACTIVITY_FOR_RESULT_PATH_CATEGORY_FILE:
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");

                intent.addCategory(Intent.CATEGORY_OPENABLE);
                break;
        }

        activity.startActivityForResult(intent,requestCode);
    }

}
