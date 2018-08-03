package com.dxtest.mvpdemo03;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dxtest.mvpdemo03.mvpeg01.MvpSimple01Activity;
import com.dxtest.mvpdemo03.mvpeg02.MVPSimple02Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setSystemStateBarTransclent(findViewById(R.id.main_toolbar));
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_jump2_simple1:
                startActivity(new Intent(this, MvpSimple01Activity.class));
                break;
            case R.id.main_jump2_simple2:
                startActivity(new Intent(this, MVPSimple02Activity.class));
                break;
        }
    }


    public void setSystemStateBarTransclent(View view){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getResources().getConfiguration().orientation= Configuration.ORIENTATION_PORTRAIT;

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        getWindow().addFlags( WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                            | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                            | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
//                            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int status_bar_height=0;
        if (identifier>0){
            status_bar_height=getResources().getDimensionPixelSize(identifier);
        }

        view.setPadding(0,status_bar_height,0,0);
    }



}
