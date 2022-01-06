/**
 * 
 */
package com.gov.nha.bis.server.util;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class RationDataDecryptUtil {

	private static final String  encKey = "ZUdUODliejEvVjkzUXhOaURkejEwM05JV2t6cXJPN2JzckVVOEl2VEg3WT06MDAwMDAwMDAwMDAwMTU0OjYzNzU3Mjg5NzgzNDA3NjI3NA==";;

	
	public static String DecryptDataStr(String toBeDecrypted)
	{ 	
		RationDataDecryptUtil crypt = new RationDataDecryptUtil();
		String ss = crypt.decrypt(toBeDecrypted, encKey);
		System.out.println(ss);
		return ss;
	}
	public  String convertToMD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException   {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		byte[] md5hash = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < md5hash.length; i++) {
			int halfbyte = (md5hash[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))	buf.append((char) ('0' + halfbyte));
				else	buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = md5hash[i] & 0x0F;
			} while(two_halfs++ < 1);
		}
		return buf.toString();  
}

	public  SecretKeySpec setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException  {
		byte[] key = myKey.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		return secretKey;
	}
	
	public  String encrypt(String Text, String salt,String pwd) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec skKey=setKey(salt);
		IvParameterSpec iv = new IvParameterSpec(pwd.getBytes(), 0, ciper.getBlockSize());
		ciper.init(Cipher.ENCRYPT_MODE, skKey, iv);
		byte[] encValue = ciper.doFinal(Text.getBytes());
		byte[] encodedBytes = Base64.encode(Base64.encode(encValue));
		
		return new String(encodedBytes);
	}

	public  String decrypt(String strToDecrypt, String salt,String pwd) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec skKey=setKey(salt);
		IvParameterSpec iv = new IvParameterSpec(pwd.getBytes(), 0, ciper.getBlockSize());
		ciper.init(Cipher.DECRYPT_MODE, skKey, iv);
		byte[] encValue = ciper.doFinal(Base64.decode(Base64.decode(strToDecrypt)));
		
		return new String(encValue);
	}

	public  String decrypt(String cipherText, String password) {
		String TextEnc ="";
		try
		{
			String saltedPassword = convertToMD5(password); 
			TextEnc = decrypt(cipherText, saltedPassword, password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return TextEnc;
	}
}