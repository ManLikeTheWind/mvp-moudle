package com.dxtest.mvpdemo03.base;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  17:15
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class BasePresenter<V extends BaseView> {

    /**
     * 绑定的View
     */
    private V mvpView;

    /**绑定view，一般在初始化中调用该方法；
     * @param mvpView
     */
    public void attachView(V mvpView){
        this.mvpView=mvpView;
    }

    /**
     * 解绑view，一般在onDestroy中调用；
     */
    public void detachView(){
        this.mvpView=null;
    }

    /**是否与View建立连接；
     * <br>每次调用业务请求的时候都要，先调用此方法检查是否与View建立连接
     * @return
     */
    public boolean isViewAttached(){
        return mvpView!=null;
    }

    /**获得连接的View
     * @return
     */
    public V getMvpView(){
        return this.mvpView;
    }

}
