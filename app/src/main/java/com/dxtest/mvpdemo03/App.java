package com.dxtest.mvpdemo03;

import android.app.Application;
import android.os.Environment;

import com.dxtest.mvpdemo03.utils.InvokeMethodOpen;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ....package: com.dxtest.mvpdemo03
 * <br>.author: dongxiang
 * <br>...date: 2018/5/3  12:00
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class App extends Application {
    public static  File mCacheFileDirDownload;
    public static  File mExternalDownload;
    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        getmCacheFileDirDownload();
        getmExternalDownloadDir();


        UnCatchException catchExcep = new UnCatchException(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }




    public static App get(){
        return instance;
    }

    private  File getmCacheFileDirDownload(){
        if (mCacheFileDirDownload==null){
            synchronized (instance){
                if (mCacheFileDirDownload==null){
                    File cacheDir = instance.getExternalCacheDir();
                    if (!cacheDir.exists()){
                        cacheDir=instance.getCacheDir();
                    }
                    FilenameFilter filter=new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            if (name.equalsIgnoreCase("download")){
                                return true;
                            }
                            return false;
                        }
                    };

                    File[] files = cacheDir.listFiles(filter);
                    if (files==null||files.length<1){
                            String cacheDirAbsolutePath=cacheDir.getAbsolutePath();
                            if (!cacheDirAbsolutePath.endsWith(File.separator)){
                                cacheDirAbsolutePath+=File.separator;
                            }
                            cacheDirAbsolutePath+="download";
                            mCacheFileDirDownload=new File(cacheDirAbsolutePath);
                            if (!mCacheFileDirDownload.exists()){
                                mCacheFileDirDownload.mkdirs();
                            }
                    }else {
                        mCacheFileDirDownload=files[0];
                    }
                }
            }
        }
        return mCacheFileDirDownload;
    }


    private File getmExternalDownloadDir(){
//        Environment environment=new Environment();
//        File rootDirectory = Environment.getRootDirectory();
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        File parentFile = externalStorageDirectory.getParentFile();
//        if (parentFile!=null){
//            File parentFile1 = parentFile.getParentFile();
//            if (parentFile1!=null){
//                File parentFile2 = parentFile1.getParentFile();
//                if (parentFile2!=null){
//                    File parentFile3 = parentFile2.getParentFile();
//                    if (parentFile3!=null){
//                        parentFile3.getParentFile();
//                    }
//                }
//            }
//
//        }
//        File file = new File("/storage");
//        File getExpandDirectory= (File) InvokeMethodOpen.invokeMethod(Environment.class,environment,"getExpandDirectory",new Class[]{});
        mExternalDownload = instance.get().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (!mExternalDownload.exists()){
            mExternalDownload=instance.getmCacheFileDirDownload();
        }
        return mExternalDownload;
    }


}
