package com.dxtest.mvpdemo03.base;

import com.dxtest.mvpdemo03.utils.InvokeMethodOpen;

/**
 * ....package: com.dxtest.mvpdemo03.base
 * <br>.author: dongxiang
 * <br>...date: 2018/5/2  16:13
 * <br>.descrp:
 * <br>..using:
 * <br>.e-mail:dongxiang_android_sdk@aliyun.com
 */

public class DataModel{

    public static BaseModel request(String token){
        BaseModel model=null;
        try{
            model=request(token,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public static BaseModel request(String token,Class<?>[]parameterTypes,Object...args){
        BaseModel model=null;
        try {
            model= (BaseModel) InvokeMethodOpen.invokeConstructor_Create(token,parameterTypes,args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public static BaseModel request(Class clazz,Class<?>[] parameterTypes,Object...args){
        BaseModel model=null;
        try{
            model= (BaseModel) InvokeMethodOpen.invokeConstructor_Create(clazz,parameterTypes,args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

}
