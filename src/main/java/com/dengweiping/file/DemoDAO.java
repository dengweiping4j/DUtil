package com.dengweiping.file;

/**
 * @description:
 * @author: DengWeiPing
 * @time: 2020/6/3 8:46
 */

import com.dengweiping.domain.DemoData;

import java.util.List;

/**
 * 假设这个是你的DAO存储。当然还要这个类让spring管理，当然你不用需要存储，也不需要这个类。
 **/
public class DemoDAO {
    public void save(List<DemoData> list) {
        System.out.println(list);
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}