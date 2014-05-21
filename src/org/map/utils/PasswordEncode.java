package org.map.utils;

import java.security.*;
import sun.misc.BASE64Encoder;

public class PasswordEncode {
	
	/**
     * 使用SHA1加密密码一次.
     * @param dataToHash 将被加密的字符串
     * @return 加密后的字符串
     *      
     */
	public static String hashSHA1String(String dataToHash)
    {
        String encodePassword = null;

        try
        {
            //One-way hash
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] byteDigest = md.digest(dataToHash.getBytes());

            //base 64 encoding, using sun's internal lib
            BASE64Encoder b64encoder = new BASE64Encoder();
            encodePassword = b64encoder.encode(byteDigest);
        }
        catch (java.security.NoSuchAlgorithmException e)
        {
            //silence and return null
        }
        
        return encodePassword;
    }
	public static void main(String[] args)
	{
		System.out.println(PasswordEncode.hashSHA1String("345"));
	}
 }

