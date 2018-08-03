/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.dxtest.mvpdemo03.mvpeg01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dxtest.mvpdemo03.R;
import com.dxtest.mvpdemo03.base.BaseActivity;

public class MvpSimple01Activity extends BaseActivity implements MvpView01 {
    public static final String TAG=MvpSimple01Activity.class.getSimpleName();
    private TextView textView;
    private MvpPresenter01 mvpPresenter01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_simple01);

        setSystemStatusTransclent(findViewById(R.id.sa1_toobar));

        textView=(TextView)findViewById(R.id.text);

        mvpPresenter01=new MvpPresenter01();
        mvpPresenter01.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter01.detachView();
    }

    public void onClick(View view){
    switch (view.getId()){
        case R.id.sa1_getData_normal:
            mvpPresenter01.getData("normal");
            break;
        case R.id.sa1_getData_finish:
                mvpPresenter01.getData("normal");
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MvpSimple01Activity.this.finish();
                    Log.e(TAG, "onClick: sa1_getData_finish");
                }
            },500);
            break;
    }
}


    @Override
    public void showData(String data) {
        textView.setText(data);
    }
}
