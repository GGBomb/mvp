package com.android.base.utils;

import android.util.Log;

import com.android.base.constance.ConfigConstant;

/**
 * 
 */
public class LogHelper {
	private static final String TAG = LogHelper.class.getSimpleName();

	public static void d(String msg) {
		if (ConfigConstant.DEBUG) {
			Log.d(_FILE_(), "[" + getLineMethod() + "]" + msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (ConfigConstant.DEBUG) {
			Log.d(TAG, "[" + getFileLineMethod() + "]" + msg);
		}
	}

	public static void i(String msg) {
		if (ConfigConstant.DEBUG) {
			Log.i(_FILE_(), "[" + getLineMethod() + "]" + msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (ConfigConstant.DEBUG) {
			Log.i(TAG, "[" + getFileLineMethod() + "]" + msg);
		}
	}

	public static void e(String msg) {
		if (ConfigConstant.DEBUG) {
			Log.e(_FILE_(), "[" + getLineMethod() + "]" + msg);
		}
	}

	public static void e(String TAG, String msg) {
		if (ConfigConstant.DEBUG) {
			Log.e(TAG, "[" + getLineMethod() + "]" + msg);
		}
	}

	public static void sysout(String msg) {
		if (ConfigConstant.DEBUG) {
			System.out.println(TAG + "Class:" + _FILE_() + "["
					+ getLineMethod() + "]" + msg);
		}
	}

	public static String getFileLineMethod() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("]");
		return toStringBuffer.toString();
	}

	public static String getLineMethod() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("]");
		return toStringBuffer.toString();
	}

	public static String _FILE_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
		return traceElement.getFileName();
	}

	public static String _FUNC_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getMethodName();
	}

	public static int _LINE_() {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		return traceElement.getLineNumber();
	}

}