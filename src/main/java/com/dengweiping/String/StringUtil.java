package com.dengweiping.String;


import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @description:字符串工具类
 * @author: DengWeiPing
 * @time: 2020/6/1 8:47
 */
public class StringUtil {

    private static Logger logger = Logger.getLogger(StringUtil.class);

    /***
     * MD5加密 生成32位md5码
     */
    public static String toMD5(String inStr) {
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            logger.error("MD5加密出错：" + e);
            e.printStackTrace();
            return null;
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    /**
     * 字节数组转换为字符串
     *
     * @return
     */
    public static String byteToStr(byte[] byt) {
        String strRead = null;
        try {
            strRead = new String(byt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("字节数组转换为字符串时出错：" + e);
            e.printStackTrace();
        }
        return strRead;
    }

    // 测试主函数
    public static void main(String args[]) {
        toMD5("dsf");
    }

}
