package com.dengweiping.collection;

import com.dengweiping.domain.DemoData;

import java.lang.reflect.Field;
import java.util.*;

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
     * 根据指定属性升序排序，忽略大小写
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
                boolean isAsc = "asc".equals(sortType);
                switch (type) {
                    case "int":
                        return isAsc ? ((int) obj1 - (int) obj2) : ((int) obj2 - (int) obj1);
                    case "long":
                    case "java.lang.Long":
                        return (long) obj1 > (long) obj2 ? (isAsc ? 1 : -1) : (isAsc ? -1 : 1);
                    case "double":
                    case "java.lang.double":
                        return (double) obj1 > (double) obj2 ? (isAsc ? 1 : -1) : (isAsc ? -1 : 1);
                    case "float":
                    case "java.lang.Float":
                        return (float) obj1 > (float) obj2 ? (isAsc ? 1 : -1) : (isAsc ? -1 : 1);
                    case "java.lang.String":
                        //循环比较次数以字符串短的为准
                        int length = obj1.toString().length() < obj2.toString().length() ? obj1.toString().length() : obj2.toString().length();
                        //是否忽略大小写
                        char[] charArray1 = ignoreCase ? obj1.toString().toLowerCase().toCharArray() : obj1.toString().toCharArray();
                        char[] charArray2 = ignoreCase ? obj2.toString().toLowerCase().toCharArray() : obj2.toString().toCharArray();
                        //比较字符串的ASCII码值
                        for (int i = 0; i < length; i++) {
                            if ((byte) charArray1[i] > (byte) charArray2[i]) {
                                return isAsc ? 1 : -1;
                            } else if ((byte) charArray1[i] < (byte) charArray2[i]) {
                                return isAsc ? -1 : 1;
                            }
                        }
                        //若相同长度部分的字符串都相等，则根据字符串长度比较
                        return isAsc ? (charArray1.length - charArray2.length) : (charArray2.length - charArray1.length);
                    case "java.util.Date":
                        Date d1 = (Date) obj1;
                        Date d2 = (Date) obj2;
                        return d1.getTime() > d2.getTime() ? (isAsc ? 1 : -1) : (isAsc ? -1 : 1);
                    default:
                        throw new RuntimeException("暂不支持 " + type + " 类型的属性排序");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        });

        return list;
    }

    /**
     * 根据指定对象属性去除重复对象
     *
     * @param list         传入的list集合
     * @param propertyName 指定的去重属性名称
     */
    public static <T> List<T> removeRepeat(List<T> list, String propertyName) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        //根据属性值进行去重
        //方式1 使用匿名内部类
        Set<T> sets = new TreeSet<T>((o1, o2) -> {
            try {
                Field field1 = o1.getClass().getDeclaredField(propertyName);
                Field field2 = o2.getClass().getDeclaredField(propertyName);
                field1.setAccessible(true);
                field2.setAccessible(true);
                Object obj1 = field1.get(o1);
                Object obj2 = field2.get(o2);
                //根据指定属性进行去重
                return obj1.toString().compareTo(obj2.toString());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        });
        sets.addAll(list);
        return new ArrayList(sets);
    }

    /**
     * 根据指定key去除重复Map
     *
     * @param list         传入的list集合
     * @param keyName 指定的key
     */
    public static List<Map<String, Object>> removeMapRepeat(List<Map<String, Object>> list, String keyName) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        //根据属性值进行去重
        //方式1 使用匿名内部类
        Set<Map<String, Object>> sets = new TreeSet<Map<String, Object>>((m1, m2) -> {
            Object obj1 = m1.get(keyName);
            Object obj2 = m2.get(keyName);
            //根据指定属性进行去重
            return obj1.toString().compareTo(obj2.toString());
        });
        sets.addAll(list);
        return new ArrayList(sets);
    }
}
