package com.dxtest.mvpdemo03.base;

import java.util.Map;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:06
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public abstract class BaseModel<T> {

    /**
     * 数据请求参数
     */
    protected String[]mParams;

    public BaseModel params(String...params){
        this.mParams=params;
        return this;
    }

    /** Get网络请求，此类看需求，由自己选择【写】或【不写】
     * @param url
     * @param callback
     *
     */
    protected   void requestGet(String url,CallBack<T> callback){
        // TODO: 2018/4/28  写具体的网络请求----Get

    }

    /**Post网络请求，此类看需求，由自己选择【写】或【不写】
     * @param url
     * @param params
     * @param callback
     */
    protected void requestPost(String url, Map params,CallBack<T> callback){
        // TODO: 2018/4/28   写具体的网络请求----post

    }

    /**
     * @param callback
     */
    public abstract void execute(CallBack<T> callback);



}
