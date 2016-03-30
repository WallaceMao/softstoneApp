package com.rishiqing.softstone.util;

import com.troyjj.crypt.Encrypt;

/**
 * 加密与解密的帮助类
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class CryptoHelper {

    public static String KEY = "DGx8IgQC";
    public static String encode(String source){
        return Encrypt.encryptSSOPlain(source, KEY);
    }

    public static String decode(String encodedString){
        return Encrypt.decryptSSOPlain(encodedString, KEY);
    }
}
