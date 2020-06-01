package com.dengweiping.collection;

import java.lang.reflect.Field;
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
        return sort(list, propertyName, "asc");
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
        return sort(list, propertyName, "desc");
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
    private static <T> List<T> sort(List<T> list, String propertyName, String sortType) {
        Collections.sort(list, (Comparator<Object>) (o1, o2) -> {
            try {
                Field field1 = o1.getClass().getDeclaredField(propertyName);
                Field field2 = o2.getClass().getDeclaredField(propertyName);
                //打开私有访问
                field1.setAccessible(true);
                field2.setAccessible(true);
                String type = field1.getType().getName();
                if ("int".equals(type)) {
                    if ("asc".equals(sortType)) {
                        //升序
                        return (int) field1.get(o1) - (int) field2.get(o2);
                    } else {
                        //降序
                        return (int) field2.get(o2) - (int) field1.get(o1);
                    }
                } else {
                    throw new RuntimeException("只支持int属性排序");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        });

        return list;
    }

    public static void main(String[] args) {

    }
}
