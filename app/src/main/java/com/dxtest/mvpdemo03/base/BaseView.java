package com.dxtest.mvpdemo03.base;

import android.content.Context;
import android.os.Bundle;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:21
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public interface BaseView {

    /**
     * 显示正在加载的View-id,以及携带的数据 args
     */
    void showLoading(int id, Bundle args);

    /**
     * 关闭正在加载的View-id
     */
    void dimissLoading(int id);

    /**显示提示
     * @param msg
     */
    void showToast(String msg,int resId);

    /**
     * 获取上下文
     * @return
     */
    Context getContext();


}
