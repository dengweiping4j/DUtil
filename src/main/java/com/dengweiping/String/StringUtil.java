package com.dengweiping.String;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @description:字符串工具类
 * @author: dengweiping
 * @time: 2020/6/1 8:47
 */
public class StringUtil {

    /**
     * MD5加密 生成32位md5码
     *
     * @param inStr 输入字符串
     * @return
     */
    public static String toMD5(String inStr) {
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
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
     * 根据指定字符串创建指定长度的随机码
     *
     * @param strTable
     * @param length
     * @return
     */
    private static String createRandomStr(String strTable, int length) {
        char[] strArr = strTable.toCharArray();
        Random random = new Random();
        StringBuffer result = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(strTable.length());
            result.append(strArr[num]);
        }
        return result.toString();
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param length 指定长度
     * @return
     */
    public static String createRandomStr(int length) {
        String strTable = "abcdefghijkmnpqrstuvwxyz";
        return createRandomStr(strTable, length);
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param length 指定长度
     * @return
     */
    public static String createRandomStrAndNum(int length) {
        String strTable = "1234567890abcdefghijkmnpqrstuvwxyz";
        return createRandomStr(strTable, length);
    }

    /**
     * 创建指定数量的随机数字
     *
     * @param length 指定长度
     * @return
     */
    public static String createRandomNum(int length) {
        String strTable = "1234567890";
        return createRandomStr(strTable, length);
    }

    /**
     * 字节数组转换为字符串
     *
     * @param bytes 字节数组
     * @return
     */
    public static String byteToStr(byte[] bytes) {
        String strRead = null;
        try {
            strRead = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strRead;
    }

    /**
     * 字符串转ASCII
     *
     * @param str 字符串
     * @return
     */
    public static int[] toASCII(String str) {
        byte[] bytes = str.getBytes();
        int[] result = new int[str.length()];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }

    /**
     * 字符转ASCII
     *
     * @param c 字符
     * @return
     */
    public static int toASCII(char c) {
        byte b = (byte) c;
        return b;
    }

    /**
     * ASCII转字符
     *
     * @param ASCIINum ASCII码值
     * @return
     */
    public static char ASCIIToChar(int ASCIINum) {
        char strChar = (char) ASCIINum;
        return strChar;
    }

    /**
     * ASCII转字符串
     *
     * @param ASCIINum ASCII码值
     * @return
     */
    public static String ASCIIToStr(int[] ASCIINum) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int num : ASCIINum) {
            char strChar = (char) num;
            stringBuffer.append(strChar);
        }
        return stringBuffer.toString();
    }

    /**
     * 字符串判空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    /**
     * 字符串判空，去掉所有空格
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.replace(" ", "");
        if (str.indexOf("\t") != -1) {
            str = str.replace("\t", "");
        }
        if (str.indexOf("\n") != -1) {
            str = str.replace("\n", "");
        }
        if (str.indexOf("\r") != -1) {
            str = str.replace("\r", "");
        }
        if (str.indexOf("\f") != -1) {
            str = str.replace("\f", "");
        }
        return isEmpty(str);
    }

}
