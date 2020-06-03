package com.dengweiping.file;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 直接用map接收数据
 *
 * @author Jiaju Zhuang
 */
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();


    @Override
    public void onException(Exception exception, AnalysisContext context) {
        System.out.println("解析失败，但是继续解析下一行:" + exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            System.out.println("第" + excelDataConvertException.getRowIndex() + "行，第" + excelDataConvertException.getColumnIndex() + "列解析异常");
        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        switch (extra.getType()) {
            case COMMENT:
                System.out.println("额外信息是批注,在rowIndex:" + extra.getRowIndex() + ",columnIndex:" + extra.getColumnIndex() + ",内容是:" + extra.getText());
                break;
            case HYPERLINK:
                if ("Sheet1!A1".equals(extra.getText())) {
                    System.out.println("额外信息是超链接,在rowIndex:" + extra.getRowIndex() + ",columnIndex:" + extra.getColumnIndex() + ",内容是:" + extra.getText());
                } else if ("Sheet2!A1".equals(extra.getText())) {
                    System.out.println("额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:" + extra.getFirstRowIndex() + "" +
                            ",firstColumnIndex:" + extra.getFirstColumnIndex() + ",lastRowIndex:" + extra.getLastRowIndex() + ",lastColumnIndex:" + extra.getLastColumnIndex() +
                            "内容是:" + extra.getText());
                } else {
                    System.out.println("Unknown hyperlink!");
                }
                break;
            case MERGE:
                System.out.println("额外信息是超链接,而且覆盖了一个区间");
                break;
            default:
        }
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        System.out.println(data);
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        System.out.println("所有数据解析完成");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println(list.size() + "条数据");
    }
}
