package com.dxtest.mvpdemo03.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethodOpen {

	public static Object invokeMethod(Class<?> classZ,Object receiver,String methodName,Class<?>[]paramsType,Object...args){
		Object imapProtocol=null;
		try {
			Method method = classZ.getDeclaredMethod(methodName, paramsType);
			method.setAccessible(true);
			imapProtocol =  method.invoke(receiver, args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return imapProtocol;
	}

	public static Object invokeVar_get(Class<?> classZ,Object receiver,String fildeName){
		Object object=null;
		try {
			Field field=classZ.getDeclaredField(fildeName);//通过Class得到类声明的属性---索取所有字段。
//			Field field1 = classZ.getField(fildeName);//通过Class得到类声明的属性----只能获取public 字段的。
			field.setAccessible(true);
			object = field.get(receiver);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return object;
	}
	public static boolean  invokeVar_set(Class<?> classZ,Object receiver,String fildeName,Object value){
		boolean result=false;
		try {
			Field field=classZ.getDeclaredField(fildeName);//通过Class得到类声明的属性---索取所有字段。
//			Field field1 = classZ.getField(fildeName);//通过Class得到类声明的属性----只能获取public 字段的。
			field.setAccessible(true);
			field.set(receiver, value);
			result=true;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Object invokeConstructor_Create(Class<?>classZ,Class<?>[]parameterTypes,Object...args){
		Object newInstance =null;
		try {
			Constructor<?> constructor = classZ.getDeclaredConstructor(parameterTypes);
			constructor.setAccessible(true);
			newInstance = constructor.newInstance(args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return newInstance;
	}

	public static Object invokeConstructor_Create(String claszPath,Class<?>[]parameterTypes,Object...args){
		Object newInstance=null;
		try {
			newInstance=invokeConstructor_Create(Class.forName(claszPath),parameterTypes,args);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return  newInstance;
	}


	
	public static class ParamsClassArgs{
		Class<?>[]parameterTypes;
		Object[]args;
		public ParamsClassArgs(Class<?>[] parameterTypes, Object[] args) {
			super();
			setParamClass(parameterTypes);
			setParamArgs(args);
		}
		public ParamsClassArgs() {
			super();
			setParamClass();
			setParamArgs();
		}
		
		public void setParamClass(Class<?>...parameterTypes){
			this.parameterTypes=parameterTypes;
			
		}
		public void setParamArgs(Object...args){
			this.args=args;
		}
	}
	
	
	
	
}
