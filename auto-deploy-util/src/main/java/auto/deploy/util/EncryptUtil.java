package auto.deploy.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @描述：加密解密类
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午10:42:56
 */
public class EncryptUtil {
	/**
	 * 加密密钥
	 */
	private static final String SITE_WIDE_SECRET = "http://www.3w2u.com";
	private static final PasswordEncoder encoder = new StandardPasswordEncoder(SITE_WIDE_SECRET);

	/**
	 * 
	 * @描述：功能描述: 密码类 加密方法：采用SHA-256算法，迭代1024次，使用一个密钥(site-widesecret)
	 *           以及8位随机盐对原密码进行加密。 随机盐确保相同的密码使用多次时，产生的哈希都不同。
	 *           密钥应该与密码区别开来存放，加密时使用一个密钥即可；对hash算法迭代执行1024次增强了安全性，使暴力破解变得更困难些。
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:43:25
	 */
	public static String encrypt(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	/**
	 * 
	 * @描述：密码匹配，登录时候调用
	 *
	 * @返回：boolean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:44:26
	 */
	public static boolean match(String rawPassword, String password) {
		return encoder.matches(rawPassword, password);
	}

	/**
	 * 
	 * @描述：BASE64编码
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:44:35
	 */
	public static String encryptBASE64(String key) {
		byte[] byteKey = key.getBytes();
		byte[] encodeKey = Base64.encode(byteKey);
		String retKey = new String(encodeKey);
		/* 转16进制输出 */
		retKey = str2hex(retKey);
		return retKey;
	}

	/**
	 * 
	 * @描述：BASE64解码
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:44:44
	 */
	public static String decryptBASE64(String key) {
		/**
		 * 转回一般的字符串再解码
		 */
		key = hex2str(key);
		byte[] byteKey = key.getBytes();
		byte[] encodeKey = Base64.decode(byteKey);
		String retKey = new String(encodeKey);
		return retKey;
	}

	/**
	 * 
	 * @描述：MD5加密（信息摘要算法），返回加密后的十六进制串(长度32).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:45:04
	 */
	public static String encryptMD5(String key) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] keyByte = md.digest();
		/* 转十六进制串 */
		String result = "";
		for (int i = 0; i < keyByte.length; i++) {
			String tmp = Integer.toHexString(keyByte[i] & 0xFF);
			if (tmp.length() == 1) {
				result += "0" + tmp;
			} else {
				result += tmp;
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述：SHA加密（安全散列算法），返回加密后的十六进制串(长度40).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:45:53
	 */
	public static String encryptSHA(String key) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(key.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] keyByte = md.digest();
		/* 转十六进制串 */
		String result = "";
		for (int i = 0; i < keyByte.length; i++) {
			String tmp = Integer.toHexString(keyByte[i] & 0xFF);
			if (tmp.length() == 1) {
				result += "0" + tmp;
			} else {
				result += tmp;
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述：DES加密（数据加密算法）.
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:46:12
	 */
	public static String encryptDES(String data, String keyStr) {
		/* 生成加密KEY */
		KeyGenerator keyGenerator = null;
		Key key = null;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(new SecureRandom(keyStr.getBytes()));
			key = keyGenerator.generateKey();
			keyGenerator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] keyByte = null;
		Cipher cipher;
		BASE64Encoder bas64 = new BASE64Encoder();
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			keyByte = cipher.doFinal(data.getBytes("UTF-8"));
		} catch (Exception e) {
			// Log.info("加密失败...");
			e.printStackTrace();
		}
		/* 加密后base64编码后返回 */
		// Log.info("加密成功...");
		String retStr = bas64.encode(keyByte);
		/* 转16进制后再输出 */
		retStr = str2hex(retStr);
		return retStr;
	}

	/**
	 * 
	 * @描述：DES解密（数据加密算法）.
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:46:34
	 */
	public static String decryptDES(String data, String keyStr) {
		/* 转回一般的字符串再解码 */
		data = hex2str(data);
		/* 生成解密KEY(和加密建一致) */
		KeyGenerator keyGenerator = null;
		Key key = null;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(new SecureRandom(keyStr.getBytes()));
			key = keyGenerator.generateKey();
			keyGenerator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cipher cipher;
		byte[] keyByte = null;
		BASE64Decoder base64 = new BASE64Decoder();
		byte[] dataByte = null;
		String retStr = null;
		try {
			/* 解密前base64编码转byte后再解密 */
			dataByte = base64.decodeBuffer(data);
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			keyByte = cipher.doFinal(dataByte);
			retStr = new String(keyByte, "UTF-8");
		} catch (BadPaddingException e) {
			// Log.info("密钥[" + keyStr + "]错误，解密失败...");
			return null;
		} catch (Exception e) {
			// Log.info("密解密失败...");
			e.printStackTrace();
			return null;
		}
		// Log.info("解密成功...");
		return retStr;
	}

	/**
	 * 
	 * @描述：字符串转十六进制串
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:48:57
	 */
	public static String str2hex(String str) {
		byte[] keyByte = str.getBytes();
		String result = "";
		for (int i = 0; i < keyByte.length; i++) {
			String tmp = Integer.toHexString(keyByte[i] & 0xFF);
			if (tmp.length() == 1) {
				result += "0" + tmp;
			} else {
				result += tmp;
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述：十六进制串转字符串
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月3日 下午10:46:55
	 */
	public static String hex2str(String str) {
		byte[] arrB = str.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return new String(arrOut);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("123"));
		System.out.println(encrypt("123"));
		System.out.println(match("123", "8758d202579ef2872f1e3992c52777a960f32d9f160e66e3f8d0737f5640d534abe7cb3380bed976"));
		System.out.println(match("123", "919721bbf712ec410c5a5fd3db990bbe6ca3cc855128bd7890a33821eac910f6188e0e1fed82f3a4"));
		/*
		 * 8758d202579ef2872f1e3992c52777a960f32d9f160e66e3f8d0737f5640d534abe7cb3380bed976
		 * 919721bbf712ec410c5a5fd3db990bbe6ca3cc855128bd7890a33821eac910f6188e0e1fed82f3a4
		 */
		System.out.println(encryptBASE64("123456你好@#￥A"));
		System.out.println(decryptBASE64("4d54497a4e445532354c3267356157395143507676365642"));

		System.out.println(encryptMD5("adfdfdf"));
		System.out.println(encryptSHA("adfdfdf222222222222"));

		System.out.println(encryptDES("fdfdfdfdfdfdfdf889", "seven"));
		System.out.println(decryptDES("663531534d6c50676d5a347a516730564d4d64752f5a736651494a726d434254", "seven"));

		System.out.println(str2hex("ffdd123中国"));
		System.out.println(hex2str("66666464313233e4b8ade59bbd"));
	}
}
