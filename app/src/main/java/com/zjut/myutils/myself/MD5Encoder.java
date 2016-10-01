package com.zjut.myutils.myself;

/**
 * 作者 @ScienceHistory
 * 时间 @2016年07月23日 16:41
 * 类说明:为密码字符串进行MD5加密
 */

import java.security.MessageDigest;

public class MD5Encoder {

    /**
     * 方法说明:加密字符串
     * @param src 源字符串
     * @return 加密后的字符串
     */
    public static String encode(String src) {
        String resultString = null;
        try {
            resultString = src;
            MessageDigest md = MessageDigest.getInstance("MD5");
            //进行加密
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ignored) {
        }
        return resultString;
    }

    /**
     * 方法说明:把字节数组转换成字符串.
     */
    private static String byte2hexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            if (((int) aByte & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) aByte & 0xff, 16));
        }
        return buf.toString();
    }
}
