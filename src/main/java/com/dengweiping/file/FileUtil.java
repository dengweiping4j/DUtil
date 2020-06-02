package com.dengweiping.file;

import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:字符串工具类
 * @author: dengweiping
 * @time: 2020/6/1 14:49
 */
public class FileUtil {

    private void test() {
        // 这里的excel文件可以 为xls或xlsx结尾
        File file = new File("E:\\测试.xls");
        List<List<String>> result = new ArrayList<>();
        try {
            ExcelOptionsService excelOptionsService = new ExcelOptionsService();
            result = excelOptionsService.writeWithoutHead(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        System.out.println("读取结果：" + result);
    }

    public static void main(String[] args) {
        new FileUtil().test();
    }
}
