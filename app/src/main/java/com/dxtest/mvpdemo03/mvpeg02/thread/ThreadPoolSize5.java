package com.dxtest.mvpdemo03.mvpeg02.thread;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ....package: com.dxtest.mvpdemo03.mvpeg02.thread
 * <br>.author: dongxiang
 * <br>...date: 2018/5/3  9:55
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class ThreadPoolSize5 {
    private static ThreadPoolSize5 instance;
    private ThreadPoolExecutor executor;
    private LinkedBlockingQueue linkedBlockingQueue;
    public static ThreadPoolSize5 get(){
        if (instance==null){
            synchronized (Object.class){
                if (instance==null){
                    instance=new ThreadPoolSize5();
                }
            }
        }
    return instance;
    }
    private ThreadPoolSize5(){
        linkedBlockingQueue=new LinkedBlockingQueue<Runnable>();//LinkedBlockingQueue.capacity默认是：Integer.maxValue

         executor=  new ThreadPoolExecutor(
                 2,//   corePoolSize		允许同时运行多少线程  :当线程数小于核心线程数时，创建线程。始终占有最少corePoolSize个线程
                 5,// maximumPoolSize       允许存在最大线程数   :当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
                 1L,//  keepAliveTime 		这个最好是设置成1 :当线程数大于等于核心线程数，且任务队列已满
                 TimeUnit.MILLISECONDS,                                     //1.若线程数小于最大线程数，创建线程,
                 linkedBlockingQueue);                                      //2.若线程数等于最大线程数，抛出异常，拒绝任务
/**
 * 线程池 总容纳量是：linkedBlockingQueue.capacity +  maximumPoolSize
	 1.当线程数小于核心线程数时，创建线程。始终占有最少corePoolSize个线程
	 2.当线程数大于等于核心线程数，2.1且任务队列未满时，将任务放入任务队列。
	                               2.2且任务队列已满
                                            2.2.1.若线程数小于最大线程数，创建线程
                                            2.2.2.若线程数等于最大线程数，抛出异常，拒绝任务
	 */
    }
    public void executor(Runnable runnable){
        Log.e("ThreadPoolSize5", "executor: runnable = "+runnable );
        executor.execute(runnable);
    }


}
