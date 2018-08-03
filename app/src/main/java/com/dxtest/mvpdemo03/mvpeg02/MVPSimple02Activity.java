package com.dxtest.mvpdemo03.mvpeg02;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dxtest.mvpdemo03.App;
import com.dxtest.mvpdemo03.R;
import com.dxtest.mvpdemo03.base.BaseActivity;
import com.dxtest.mvpdemo03.base.DataModel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MVPSimple02Activity extends BaseActivity implements MvpView02 {
    public static final String TAG=MVPSimple02Activity.class.getSimpleName();

    private ProgressBar sa2_progressBar;
    private MvpPresenter02 mvpPresenter02;

    private String mSourceFilePath;
    private String mDestFilePath;
    private long mCurrentPosition;

    public static final int LEN_TIME_BREAK=3;

    public static final int REQUESTCODE_PATH_CATEGORY=1;
    public static final int REQUESTCODE_PATH_CATEGORY_FILE=REQUESTCODE_PATH_CATEGORY+1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpsimple02);
        setSystemStatusTransclent(findViewById(R.id.sa2_toobar));

        sa2_progressBar= (ProgressBar) findViewById(R.id.sa2_progressBar);

        initData();

    }
    private void initData(){
        mvpPresenter02=new MvpPresenter02();
        mvpPresenter02.attachView(this);

        if (App.mExternalDownload!=null){
            File fileSource=new File(App.mExternalDownload,"test_randomaccessFile.txt");
            if (!fileSource.exists()){
                try {
                    fileSource.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mSourceFilePath=fileSource.getAbsolutePath();

            File fileDest=new File(App.mExternalDownload,"test_randomaccessFile_copy.txt");
            if (fileDest.exists()){
                fileDest.delete();
            }
            mDestFilePath=fileDest.getAbsolutePath();
        }

        Log.e(TAG, "initData: mSourceFilePath = "+mSourceFilePath+";mDestFilePath = "+mDestFilePath );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUESTCODE_PATH_CATEGORY_FILE:
                if (resultCode!=RESULT_OK){
                    break;
                }
                String dataString = data.getDataString();
                Uri uri = data.getData();
                Log.e(TAG, "onActivityResult: REQUESTCODE_PATH_CATEGORY dataString = "+dataString+"; uri = "+uri);
                break;
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter02.detachView();
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_upload:
                final MVPSimple02Activity mvpSimple02Activity=this;
                MVPSimple02Activity sss= (MVPSimple02Activity) Proxy.newProxyInstance(MVPSimple02Activity.class.getClassLoader(), MVPSimple02Activity.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(mvpSimple02Activity,args);
                    }
                });
                mvpPresenter02.getData(mSourceFilePath,mDestFilePath,mCurrentPosition);
                break;
            case R.id.button_jump_category:
                mvpPresenter02.startActivityForResult(this,PkgConst.START_ACTIVITY_FOR_RESULT_PATH_CATEGORY,REQUESTCODE_PATH_CATEGORY);
                break;
            case R.id.button_jump_category_file:
                mvpPresenter02.startActivityForResult(this,PkgConst.START_ACTIVITY_FOR_RESULT_PATH_CATEGORY_FILE,REQUESTCODE_PATH_CATEGORY_FILE);
                break;
        }
    }

    @Override
    public void onProgress(long currentPosition,long total) {
        mCurrentPosition=currentPosition;

        int max = sa2_progressBar.getMax();
        int progress = 0 ;
        if (currentPosition>total){
            progress=max;
        }else {
            progress=(int) (currentPosition*max  / total+0.5);
        }
        sa2_progressBar.setProgress(progress);
    }
}
