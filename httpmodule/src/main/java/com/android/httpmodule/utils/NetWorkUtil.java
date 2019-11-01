package com.android.httpmodule.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * 网络信息类
 * 
 */
public class NetWorkUtil {

	/**
	 * 检测网络是否可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取手机MAC地址
	 * 
	 * @return
	 */
	public static String getMAC(Context context) {
		String mac = "";
		try {
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			mac = wifiInfo.getMacAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mac;
	}

	/**
	 * 检测网络类型
	 */
	public static String getNetType(Context context) {
		String netType = "";
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						// netType = info[i].getTypeName();

						if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
							netType = "WIFI";
						} else if (info[i].getType() == ConnectivityManager.TYPE_MOBILE) {
							String _strSubTypeName = info[i].getSubtypeName();

							// TD-SCDMA networkType is 17
							int networkType = info[i].getSubtype();
							switch (networkType) {
							case TelephonyManager.NETWORK_TYPE_GPRS:
							case TelephonyManager.NETWORK_TYPE_EDGE:
							case TelephonyManager.NETWORK_TYPE_CDMA:
							case TelephonyManager.NETWORK_TYPE_1xRTT:
							case TelephonyManager.NETWORK_TYPE_IDEN: // api<8 :
																		// replace
																		// by 11
								netType = "2G";
								break;
							case TelephonyManager.NETWORK_TYPE_UMTS:
							case TelephonyManager.NETWORK_TYPE_EVDO_0:
							case TelephonyManager.NETWORK_TYPE_EVDO_A:
							case TelephonyManager.NETWORK_TYPE_HSDPA:
							case TelephonyManager.NETWORK_TYPE_HSUPA:
							case TelephonyManager.NETWORK_TYPE_HSPA:
							case TelephonyManager.NETWORK_TYPE_EVDO_B: // api<9
																		// :
																		// replace
																		// by 14
							case TelephonyManager.NETWORK_TYPE_EHRPD: // api<11
																		// :
																		// replace
																		// by 12
							case TelephonyManager.NETWORK_TYPE_HSPAP: // api<13
																		// :
																		// replace
																		// by 15
								netType = "3G";
								break;
							case TelephonyManager.NETWORK_TYPE_LTE: // api<11 :
																	// replace
																	// by 13
								netType = "4G";
								break;
							default:
								// http://baike.baidu.com/item/TD-SCDMA 中国移动 联通
								// 电信 三种3G制式
								if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA")
										|| _strSubTypeName.equalsIgnoreCase("WCDMA")
										|| _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
									netType = "3G";
								} else {
									netType = _strSubTypeName;
								}

								break;
							}
						}
					}
				}
			}
		}
		return netType;
	}
}
