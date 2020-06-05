package com.dengweiping.String;

import java.util.regex.Pattern;

/**
 * @description: 常用校验规则
 * @author: DengWeiPing
 * @time: 2020/6/5 14:21
 */
public class ValidatorUtil {

    /**
     * 正则表达式：验证邮箱
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    private static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    private static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 非负整数（正整数   +   0）
     */
    private static final String NON_NEGATIVE_INTEGER = "^\\d+$";
    /**
     * 正整数
     */
    private static final String POSITIVE_INTEGER = "^[0-9]*[1-9][0-9]*$";

    /**
     * 非正整数（负整数   +   0）
     */
    private static final String NON_POSITIVE_INTEGER = "^((-\\d+)|(0+))$";
    /**
     * 负整数
     */
    private static final String NEGATIVE_INTEGER = "^-[0-9]*[1-9][0-9]*$";
    /**
     * 整数
     */
    private static final String INTEGER = "^-?\\d+$";
    /**
     * 非负浮点数（正浮点数   +   0）
     */
    private static final String NON_NEGATIVE_FLOATING_POINT = "^\\d+(\\.\\d+)?$";
    /**
     * 正浮点数
     */
    private static final String POSITIVE_FLOATING_POINT = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
    /**
     * 非正浮点数（负浮点数   +   0）
     */
    private static final String NON_POSITIVE_FLOATING_POINT = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
    /**
     * 负浮点数
     */
    private static final String NEGATIVE_FLOAT = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
    /**
     * 浮点数
     */
    private static final String FLOATING_POINT = "^(-?\\d+)(\\.\\d+)?$";

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 校验非负整数（正整数   +   0）
     *
     * @param num
     * @return
     */
    public static boolean isNonNegativeInteger(String num) {
        return Pattern.matches(NON_NEGATIVE_INTEGER, num);
    }

    /**
     * 正整数
     *
     * @param num
     * @return
     */
    public static boolean isPositiveInteger(String num) {
        return Pattern.matches(POSITIVE_INTEGER, num);
    }


    /**
     * 非正整数（负整数   +   0）
     *
     * @param num
     * @return
     */
    public static boolean isNonPositiveInteger(String num) {
        return Pattern.matches(NON_POSITIVE_INTEGER, num);
    }

    /**
     * 负整数
     *
     * @param num
     * @return
     */
    public static boolean isNegativeInteger(String num) {
        return Pattern.matches(NEGATIVE_INTEGER, num);
    }

    /**
     * 整数
     *
     * @param num
     * @return
     */
    public static boolean isInteger(String num) {
        return Pattern.matches(REGEX_IP_ADDR, num);
    }

    /**
     * 非负浮点数（正浮点数   +   0）
     *
     * @param num
     * @return
     */
    public static boolean isNonNegativeFloatingPoint(String num) {
        return Pattern.matches(NON_NEGATIVE_FLOATING_POINT, num);
    }

    /**
     * 正浮点数
     *
     * @param num
     * @return
     */
    public static boolean isPositiveFloatingPoint(String num) {
        return Pattern.matches(POSITIVE_FLOATING_POINT, num);
    }

    /**
     * 非正浮点数（负浮点数   +   0）
     *
     * @param num
     * @return
     */
    public static boolean isNonPositiveFloatingPoint(String num) {
        return Pattern.matches(NON_POSITIVE_FLOATING_POINT, num);
    }

    /**
     * 负浮点数
     *
     * @param num
     * @return
     */
    public static boolean isNegativeFloat(String num) {
        return Pattern.matches(NEGATIVE_FLOAT, num);
    }

    /**
     * 浮点数
     *
     * @param num
     * @return
     */
    public static boolean isFloatingPoint(String num) {
        return Pattern.matches(FLOATING_POINT, num);
    }
}
