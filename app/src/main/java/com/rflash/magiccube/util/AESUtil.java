package com.rflash.magiccube.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	/*
	 * 转为十六进制
	 */
	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	/*
	 * 转为二进�?
	 */
	private static byte[] asBin(String src) {
		if (src.length() < 1)
			return null;
		byte[] encrypted = new byte[src.length() / 2];
		for (int i = 0; i < src.length() / 2; i++) {
			int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}

	/*
	 * 加密
	 */
	public static String encrypt(String data, String secret_key) {
		byte[] key = asBin(secret_key);
		SecretKeySpec sKey = new SecretKeySpec(key, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, sKey);
			byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
			return asHex(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 解密
	 */
	public static String decrypt(String encData, String secret_key) {
		byte[] tmp = asBin(encData);
		byte[] key = asBin(secret_key);
		SecretKeySpec sKey = new SecretKeySpec(key, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, sKey);
			byte[] decrypted = cipher.doFinal(tmp);
			return new String(decrypted, "utf-8");
		} catch (Exception e) {
			return null;
		}
	}

	public static String getAutoCreateAESKey() {
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		kg.init(128);// 要生成多少位，只�?要修改这里即�?128, 192�?256
		SecretKey sk = kg.generateKey();
		byte[] b = sk.getEncoded();
		return asHex(b);
	}
}