
package com.rflash.magiccube.util;


import android.text.TextUtils;
import android.util.Log;

import java.util.Map.Entry;
import java.util.TreeMap;



public class SignUtil {

	public static final String TAG = "SignUtil";

	public static String signData(TreeMap<String, String> treeMap, String privateKeyFile) throws Exception {
		TreeMap<String, String> tMap = new TreeMap<String, String>();
		for (Entry<String, String> entry : treeMap.entrySet()) {
			if (!TextUtils.isEmpty(entry.getValue())) {
				tMap.put(entry.getKey(), entry.getValue());
			}
		}
		StringBuffer buf = new StringBuffer();
		for (String key : tMap.keySet()) {
			buf.append(key).append("=").append((String) tMap.get(key)).append("&");
		}
		String signatureStr = buf.substring(0, buf.length() - 1);
		String signData = RSACoder.signMS(signatureStr, privateKeyFile);
		return signData;
	}

	public static boolean verferSignData(TreeMap<String, String> treeMap, String publicKeyFile) throws Exception {
		if (treeMap != null && treeMap.size() > 0) {
			StringBuffer buf = new StringBuffer();
			String signature = "";
			for (String key : treeMap.keySet()) {
				if ("signature".equals(key)) {
					signature = treeMap.get(key).replace(" ", "+");
				} else if(!TextUtils.isEmpty(treeMap.get(key))){
					buf.append(key).append("=").append((String) treeMap.get(key)).append("&");
				}else{
					//����Ϊ�յ��ֶ�
				}
			}
			String signatureStr = buf.substring(0, buf.length() - 1);
			return RSACoder.verifyMS(signatureStr, signature, publicKeyFile);
		}
		return false;
	}
	
	//-----------------------------------------------------------------
	
	public static String signDataWithStr(TreeMap<String, String> treeMap, String privateKeyStr) throws Exception {
		TreeMap<String, String> tMap = new TreeMap<String, String>();
		for (Entry<String, String> entry : treeMap.entrySet()) {
			if (!TextUtils.isEmpty(entry.getValue())) {
				if(!"signature".equals(entry.getKey())){
					tMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		StringBuffer buf = new StringBuffer();
		for (String key : tMap.keySet()) {
			buf.append(key).append("=").append((String) tMap.get(key)).append("&");
		}
		String signatureStr = buf.substring(0, buf.length() - 1);
		String signData = RSACoder.signMSWithKeyStr(signatureStr, privateKeyStr);
		Log.i("signatureStr",signatureStr);
		return signData;
	}
	
	public static boolean verferSignDataWithStr(TreeMap<String, String> treeMap, String publicKeyStr) throws Exception {
		if (treeMap != null && treeMap.size() > 0) {
			StringBuffer buf = new StringBuffer();
			String signature = "";
			for (String key : treeMap.keySet()) {
				if ("signature".equals(key)) {
					signature = treeMap.get(key).replace(" ", "+");
				} else if(!TextUtils.isEmpty(treeMap.get(key))){
					buf.append(key).append("=").append((String) treeMap.get(key)).append("&");
				}else{
					//忽略空字段
				}
			}
			String signatureStr = buf.substring(0, buf.length() - 1);
			Log.i(TAG,signatureStr);
			return RSACoder.verifyMSWithStr(signatureStr, signature, publicKeyStr);
		}
		return false;
	}
}
