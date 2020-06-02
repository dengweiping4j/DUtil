package com.dengweiping.collection;

import com.dengweiping.domain.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description 集合工具类
 * @author: dengweiping
 * @time: 2020/6/1 14:49
 */
public class ListUtil {

    /**
     * 根据指定属性升序排序
     *
     * @param list         集合
     * @param propertyName 属性名
     * @param <T>
     * @return
     */
    public static <T> List<T> sortAsc(List<T> list, String propertyName) {
        return sort(list, propertyName, "asc", false);
    }

    /**
     * 根据指定属性升序排序
     *
     * @param list         集合
     * @param propertyName 属性名
     * @param <T>
     * @return
     */
    public static <T> List<T> sortAscIgnoreCase(List<T> list, String propertyName) {
        return sort(list, propertyName, "asc", true);
    }

    /**
     * 根据指定属性降序排序
     *
     * @param list         集合
     * @param propertyName 属性名
     * @param <T>
     * @return
     */
    public static <T> List<T> sortDesc(List<T> list, String propertyName) {
        return sort(list, propertyName, "desc", false);
    }

    /**
     * 根据指定属性降序排序，忽略大小写
     *
     * @param list         集合
     * @param propertyName 属性名
     * @param <T>
     * @return
     */
    public static <T> List<T> sortDescIgnoreCase(List<T> list, String propertyName) {
        return sort(list, propertyName, "desc", true);
    }

    /**
     * 根据指定方式排序
     *
     * @param list         集合
     * @param propertyName 属性名
     * @param sortType     排序方式 asc：升序 desc：降序
     * @param <T>
     * @return
     */
    private static <T> List<T> sort(List<T> list, String propertyName, String sortType, boolean ignoreCase) {
        Collections.sort(list, (Comparator<Object>) (o1, o2) -> {
            try {
                Field field1 = o1.getClass().getDeclaredField(propertyName);
                Field field2 = o2.getClass().getDeclaredField(propertyName);
                //打开私有访问
                field1.setAccessible(true);
                field2.setAccessible(true);
                String type = field1.getType().getName();
                Object obj1 = field1.get(o1);
                Object obj2 = field2.get(o2);
                if (obj1 == null) {
                    return -1;
                } else if (obj2 == null) {
                    return 1;
                }
                if ("int".equals(type)) {
                    if ("asc".equals(sortType)) {
                        //升序
                        return (int) obj1 - (int) obj2;
                    } else {
                        //降序
                        return (int) obj2 - (int) obj1;
                    }
                } else if ("java.lang.String".equals(type)) {
                    //循环比较次数以字符串短的为准
                    int length = obj1.toString().length() < obj2.toString().length() ? obj1.toString().length() : obj2.toString().length();
                    //是否忽略大小写
                    char[] charArray1 = ignoreCase ? obj1.toString().toLowerCase().toCharArray() : obj1.toString().toCharArray();
                    char[] charArray2 = ignoreCase ? obj2.toString().toLowerCase().toCharArray() : obj2.toString().toCharArray();
                    //比较字符串的ASCII码值
                    if ("asc".equals(sortType)) {
                        for (int i = 0; i < length; i++) {
                            if ((byte) charArray1[i] > (byte) charArray2[i]) {
                                return 1;
                            } else if ((byte) charArray1[i] < (byte) charArray2[i]) {
                                return -1;
                            }
                        }
                        //若相同长度部分的字符串都相等，则根据字符串长度比较
                        return charArray1.length - charArray2.length;
                    } else {
                        for (int i = 0; i < length; i++) {
                            if ((byte) charArray1[i] > (byte) charArray2[i]) {
                                return -1;
                            } else if ((byte) charArray1[i] < (byte) charArray2[i]) {
                                return 1;
                            }
                        }
                        //若相同长度部分的字符串都相等，则根据字符串长度比较
                        return charArray2.length - charArray1.length;
                    }
                } else {
                    throw new RuntimeException("只支持int和String属性排序");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        });

        return list;
    }

}
