package com.dps.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptographyUtils {

	public static SecretKeySpec getKey(String keyData) throws Exception {
		byte[] key = keyData.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		key = md.digest(key);
		key = Arrays.copyOf(key, 16);
		return new SecretKeySpec(key, "AES");
	}
	
	public static String encryptAES256(String data, String salt) throws Exception {
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, getKey(salt));
		return Base64.getEncoder().encodeToString(c.doFinal(data.getBytes("UTF-8")));
	}
	
	public static String decryptAES256(String data, String salt) throws Exception {
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, getKey(salt));
		return new String(c.doFinal(Base64.getDecoder().decode(data)));
	}
	
	public static String encryptAES256(byte[] data, String salt) throws Exception {
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, getKey(salt));
		return Base64.getEncoder().encodeToString(c.doFinal(data));
	}
	
	public static byte[] decryptAES256(byte[] data, String salt) throws Exception {
		Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, getKey(salt));
		return c.doFinal(Base64.getDecoder().decode(data));
	}
	
}
