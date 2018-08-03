package com.dxtest.mvpdemo03.base;

/**
 * <br>package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:02
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */
public interface CallBack<T> {
    /**
     * <b>进度回调</b>
     * @param currentPosition
     * @param total
     */
    void onProgressCallBack(long currentPosition,long total);
    /**
     * <b>请求成功</b>
     * @param data 请求到的数据
     */
    void onSuccess(T data);

    /**<b>请求失败</b>
     * <p>使用网络API 接口请求方式时;
     *      <br>虽然已经请求成功，但是由于{@code data}的原因 无法正常返回数据；
     * </p>
     */
    void onFailure(String data);

    /**
     * <b>请求失败</b>
     * <p>请求网络API接口请求方式时：
     *  <br>因无法联网、缺少权限，内存泄漏等原因导致无法连接到请求数据源；
     * </p>
     */
    void onError();


    /**
     * <b>请求调用完全结束</b>
     * <p>
     *     无论请求结果是成功、失败 或 抛出异常都会执行此方法，给用处理；
     *     <br>通常做网络请求是可以在此处隐藏“正在加载”的等待的控件；
     * </p>
     */
    void onComplete();

}