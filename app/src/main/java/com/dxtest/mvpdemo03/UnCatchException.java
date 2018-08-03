package com.dxtest.mvpdemo03;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;

public class UnCatchException implements UncaughtExceptionHandler {
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	public static final String TAG = UnCatchException.class.getSimpleName();
	private Context mContext;

	public UnCatchException(App application) {
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		mContext = application.getApplicationContext();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		String versioninfo = getVersionInfo();
		String mobileInfo = getMobileInfo();
		String errorinfo = getErrorInfo(ex);
		Log.e(TAG, "errorinfo--> "
				+"versioninfo: " +versioninfo+"\n"
				+"mobileInfo: " +mobileInfo+"\n"
				+ errorinfo);
	}

	/** 获取错误的信 */
	private String getErrorInfo(Throwable arg1) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		arg1.printStackTrace(pw);
		pw.close();
		String error = writer.toString();
		return error;
	}

	/**获取手机的硬件信*/
	private String getMobileInfo() {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();
				String value = field.get(null).toString();
				sb.append(name + "=" + value);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**获取手机的版本信*/
	private String getVersionInfo() {
		try {
			PackageManager pm = mContext.getApplicationContext()
					.getPackageManager();
			PackageInfo info = pm.getPackageInfo(mContext
					.getApplicationContext().getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "UnKnown-Mobile-Version-Info";
		}
	}

}
