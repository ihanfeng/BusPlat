package me.vliupro.smb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vliupro on 16-5-23.
 */
public class Md5 {

    public static String encryption(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        byte[] strByte = str.getBytes();
        byte[] cipherData = md5.digest(strByte);
        StringBuffer buffer = new StringBuffer();
        for (byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            buffer.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }

        return String.valueOf(buffer);
    }

}
