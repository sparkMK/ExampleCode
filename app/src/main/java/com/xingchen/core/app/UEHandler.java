package com.xingchen.core.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xingchen.core.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常处理类
 * @author pingping.hu
 *
 */
public class UEHandler implements Thread.UncaughtExceptionHandler {
	private static SimpleDateFormat fileTimeFormat  = new SimpleDateFormat("MM-dd-HH-mm");
	private static final String TAG = "UEHandler";
	private Context context;
	private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
	private static UEHandler mInstance;// CrashHandler实例
	
	private UEHandler() {

	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果自定义的没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		}
	}

	/** 获取UEHandler实例 ,单例模式 */
	public static UEHandler getInstance() {
		if (mInstance == null){
			mInstance = new UEHandler();
		}
		return mInstance;
	}

	/**
	 * 初始化
	 * @param context
	 */
	public void initUEHandler(Context context) {
		this.context = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
		Thread.setDefaultUncaughtExceptionHandler(this);// 设置该UEHandler为程序的默认处理器
	}
	
	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex 异常信息
	 * @return true 如果处理了该异常信息;否则返回false.
	 */
	public boolean handleException(Throwable ex) {
		if (ex == null || context == null){
			return false;
		}
		if(null == this.getClass()){
			return false;
		}
		//LoggerUtil.getLogger(this.getClass()).logThrowable(ex);
		final String ueHandlerReport = getUEHandlerReport(context, ex);
		//暂时关闭异常反馈
		new Thread() {
			public void run() {
				Looper.prepare();
				save2File(ueHandlerReport);
				Looper.loop();
			}

		}.start();
		return true;
	}
	
	private void save2File(String ueHandlerReport) {
		String fileName = "ueHandlerReport-" + getDataTime(fileTimeFormat) + ".txt";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File dir = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator + "TakeCare" + File.separator + "crash");
				if (!dir.exists()){
					dir.mkdir();
				}
				File file = new File(dir, fileName);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(ueHandlerReport.toString().trim().getBytes());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取APP崩溃异常报告
	 * 
	 * @param ex
	 * @return
	 */
	private String getUEHandlerReport(Context context, Throwable ex) {
		PackageInfo pinfo = getPackageInfo(context);
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version: " + pinfo.versionName + "("
				+ pinfo.versionCode + ")\n");
		exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
				+ "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception: " + ex.getMessage() + "\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}
	
	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	private PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info == null){
			info = new PackageInfo();
		}
		return info;
	}

	//获取当前时间字符串
	public String getDataTime(SimpleDateFormat dataFormat){
		return dataFormat.format(new Date());
	}


}