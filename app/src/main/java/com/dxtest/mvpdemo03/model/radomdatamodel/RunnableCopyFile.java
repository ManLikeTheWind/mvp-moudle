package com.dxtest.mvpdemo03.model.radomdatamodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.dxtest.mvpdemo03.base.CallBack;
import com.dxtest.mvpdemo03.mvpeg02.MVPSimple02Activity;
import com.dxtest.mvpdemo03.utils.MemoryStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ....package: com.dxtest.mvpdemo03.model.radomdatamodel
 * <br>.author: dongxiang
 * <br>...date: 2018/5/3  10:14
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class RunnableCopyFile implements Runnable {
    public static final  String  TAG=RunnableCopyFile.class.getSimpleName();

    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case PkgConst.FAILURE:
                    mCallback.onFailure(msg.obj+"");
                    break;
                case PkgConst.ERROR:
                    mCallback.onError();
                    break;
                case PkgConst.SUCCESS:
                    mCallback.onSuccess(msg.obj+"");
                    break;
                case PkgConst.PROGRESS:
                    Bundle data = msg.getData();
                    long currentPosition = data.getLong(PkgConst.MSG_KEY_CURRENT_POSITION);
                    long total = data.getLong(PkgConst.MSG_KEY_TOTAL);
                    mCallback.onProgressCallBack(currentPosition,total);
                    break;
                case PkgConst.COMPLETE:
                    mCallback.onComplete();
                    break;
            }
            return false;
        }
    });

    private String mSourceFilePath;
    private String mDestFilePath;
    private long mCurrentPosition;
    private CallBack<String> mCallback;

    private long mTotal;

    public RunnableCopyFile(String sourceFilePath,String destFilePath,long currentPosition,CallBack<String> callback){
        mSourceFilePath=sourceFilePath;
        mDestFilePath=destFilePath;
        mCurrentPosition=currentPosition;
        mCallback=callback;
    }

    @Override
    public void run() {
        RandomAccessFile randomAccessFileSource=null;
        RandomAccessFile randomAccessFileDest=null;
        try {
            File sourceFile=new File(mSourceFilePath);
            if (!sourceFile.exists()){
                mHandler.sendMessage(mHandler.obtainMessage(PkgConst.FAILURE,mSourceFilePath+" 文件不存在"));
                return;
            }
            mTotal=sourceFile.length();
            long freeSpace = sourceFile.getFreeSpace();
            long usableSpace = sourceFile.getUsableSpace();
            long totalSpace = sourceFile.getTotalSpace();

            String destParentFolder=mDestFilePath.substring(0,mDestFilePath.lastIndexOf(File.separator));
            long specificMemoryAvaliable_sourceParentFolder = MemoryStatus.getSpecificMemoryAvaliable(destParentFolder);

            long specificMemoryAvaliable_mSourceFilePath = MemoryStatus.getSpecificMemoryAvaliable(mSourceFilePath);
//run  freeSpace = 1953636352:usableSpace = 1936859136;totalSpace = 5278638080;specificMemoryAvaliable_sourceParentFolder = 1936859136;specificMemoryAvaliable_mSourceFilePath = 1936859136
            Log.e(TAG,Thread.currentThread()
                    +" run  freeSpace = "+freeSpace
                    +":usableSpace = "+usableSpace
                    +";totalSpace = "+totalSpace
                    +";specificMemoryAvaliable_sourceParentFolder = "+specificMemoryAvaliable_sourceParentFolder
                    +";specificMemoryAvaliable_mSourceFilePath = "+specificMemoryAvaliable_mSourceFilePath);

            if (!MemoryStatus.isSpecificMemoryAvailable(mTotal,destParentFolder)){
                mHandler.sendMessage(mHandler.obtainMessage(PkgConst.FAILURE,mSourceFilePath + " 内存不足"));
                return;
            }

            File destFileFolder=new File(destParentFolder);
            if (!destFileFolder.exists()){
                destFileFolder.mkdirs();
            }
            File destFile=new File(mDestFilePath);

            randomAccessFileSource=new RandomAccessFile(sourceFile,"rwd");
            randomAccessFileSource.seek(mCurrentPosition);

            randomAccessFileDest=new RandomAccessFile(destFile,"rwd");
            randomAccessFileDest.seek(mCurrentPosition);

            int per = (int) (mTotal / 50);
            byte[] buff=new byte[per];
            int len=-1;
            int i=0;
            Bundle bundle=null;
            while ((len=randomAccessFileSource.read(buff))>0){
                if (i>MVPSimple02Activity.LEN_TIME_BREAK){
                    throw new BreakException("模拟中断事件 i= "+i+";MVPSimple02Activity.LEN_TIME_BREAK "+MVPSimple02Activity.LEN_TIME_BREAK);
                }
                i++;

                randomAccessFileDest.write(buff,0,len);

                bundle=new Bundle();
                bundle.putLong(PkgConst.MSG_KEY_TOTAL,mTotal);
                mCurrentPosition+=len;
                bundle.putLong(PkgConst.MSG_KEY_CURRENT_POSITION,mCurrentPosition);
                Log.e(TAG, "run: buff.length = " + buff.length + ";len = "+len+";mCurrentPosition = "+mCurrentPosition );

                Message message = mHandler.obtainMessage(PkgConst.PROGRESS);
                message.setData(bundle);

                mHandler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            mHandler.sendMessage(mHandler.obtainMessage(PkgConst.SUCCESS," 复制完成"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mHandler.sendMessage(mHandler.obtainMessage(PkgConst.FAILURE,e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.sendMessage(mHandler.obtainMessage(PkgConst.FAILURE,e.getMessage()));
        }catch (BreakException e){
            e.printStackTrace();
            mHandler.sendMessage(mHandler.obtainMessage(PkgConst.FAILURE,e.getMessage()));
        }finally {
                try {
                    if (randomAccessFileSource!=null){
                       randomAccessFileSource.close();
                    }
                    if (randomAccessFileDest!=null){
                        randomAccessFileDest.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            mHandler.sendMessage(mHandler.obtainMessage(PkgConst.COMPLETE));
        }
    }

    public class BreakException extends RuntimeException{
        public BreakException(String message) {
            super(message);
        }
    }



}
