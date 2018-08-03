package com.dxtest.mvpdemo03.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.dxtest.mvpdemo03.utils.DialogUtils;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:28
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog mCommonProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//---------------重写onCreateDialog-start

    /**
     * showDialog(DialogUtils.DIALOG_MSG,bundle)
     * removeDialog(DialogUtils.DIALOG_MSG);
     * dimissDialog(DialogUtils.DIALOG_MSG);
     *
     * @param id
     * @param args
     * @return
     */
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        String messageMsg = "DIALOG_MSG-messageMsg";
        String titleMsg = "DIALOG_MSG-titleMsg";
        Dialog dialog = null;
        switch (id) {
            case DialogUtils.DIALOG_MSG:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setMessage(messageMsg);
                mBuilder.setTitle(titleMsg);
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeDialog(DialogUtils.DIALOG_MSG);
                    }
                });
                mBuilder.setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeDialog(DialogUtils.DIALOG_MSG);
                    }
                });
                dialog = mBuilder.create();
                break;
            case DialogUtils.DIALOG_PROGRESS:
                dialog = new ProgressDialog(this);
                ((ProgressDialog) dialog).setMessage(messageMsg);
                dialog.setCancelable(false);
                dialog.setTitle(titleMsg);
                ((ProgressDialog) dialog).setButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeDialog(DialogUtils.DIALOG_PROGRESS);
                    }
                });
                break;
            case DialogUtils.DIALOG_COMM_PROGRESS:
//                if (mCommonProgressDialog==null){
//                    mCommonProgressDialog=new ProgressDialog(this);
//                    mCommonProgressDialog.setCancelable(false);
//                }
//                if (!mCommonProgressDialog.isShowing()){
//                    mCommonProgressDialog.show();
//                }
                dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                break;
        }
        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        super.onPrepareDialog(id, dialog, args);
    }
//---------------重写onCreateDialog-end

    @Override
    public void showLoading(int id, Bundle args) {
        showDialog(id, null);
    }

    @Override
    public void dimissLoading(int id) {
        removeDialog(id);
    }

    @Override
    public void showToast(String msg, int resId) {
        Toast toast = Toast.makeText(this,"",resId);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        if (!TextUtils.isEmpty(msg)) {
            toast.setText(msg);
            toast.show();
        }else if (resId>0){
            toast.setText(resId);
            toast.show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

//    ================设置状态栏
    /** 对Activity顶部嵌套的title_bar 进行状态栏透明化 */
    public  void setSystemStatusTransclent(View view){
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉系统的状态栏----要放到setContentView(R.layout.activity_main); 之前

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getResources().getConfiguration().orientation = Configuration.ORIENTATION_PORTRAIT;

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        int status_bar_height_id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int status_bar_height=0;
        if (status_bar_height_id>0){
            status_bar_height = getResources().getDimensionPixelSize(status_bar_height_id);
        }

        view.setPadding(0,status_bar_height,0,0);
    }
    /**
     * 对Activity顶部的状态栏进行透明化 适用于顶部没有嵌入"title bar"的Activity,背景是一张图片或者没有导航栏的情况
     * 但是需要在Activity对应的布局xml文件里,添加下面两个属性: android:fitsSystemWindows="true"
     * android:clipToPadding="true" 以及根据情况设置背景色
     * android:background="#2869cc"(背景图片没有铺满整个布局)
     * 详情请参考"LoginActivity",LoginActivity顶部是没有嵌套"title bar"的
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void getSystemStatusBarTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /** 设置全屏还是不是全屏*/
    public  void attacherFull(boolean isSetFull){
        if (isSetFull) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(params);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }else {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(params);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


}
