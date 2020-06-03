package com.dengweiping.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dengweiping.domain.DemoData;

import java.util.*;

/**
 * @description:字符串工具类
 * @author: dengweiping
 * @time: 2020/6/1 14:49
 */
public class FileUtil {

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setName("张" + i);
            data.setNumber(i*3+5);
            list.add(data);
        }
        return list;
    }

    public void write() {
        // 写法1
        String fileName = "E:\\" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

//        // 写法2
//        fileName = System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//        excelWriter.write(data(), writeSheet);
//        // &#x5343;&#x4E07;&#x522B;&#x5FD8;&#x8BB0;finish &#x4F1A;&#x5E2E;&#x5FD9;&#x5173;&#x95ED;&#x6D41;
//        excelWriter.finish();
    }

    public void templateWrite() {
        String templateFileName = "E:\\demo.xlsx";
        String fileName = "E:\\" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).withTemplate(templateFileName).sheet().doWrite(data());
    }

    public void read() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "E:\\demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
        // 写法2：
        fileName = "E:\\demo.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    public void simpleFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "E:\\demo.xlsx";
        String fileName = "E:\\" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(data(), writeSheet);
        excelWriter.fill(data(), writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
    }

    public static void main(String[] args) {
        new FileUtil().simpleFill();
    }
}
