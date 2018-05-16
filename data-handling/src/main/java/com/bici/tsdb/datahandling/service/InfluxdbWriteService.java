package com.bici.tsdb.datahandling.service;

import com.bici.tsdb.common.entity.Payload;

/**
 * InfluxDB写入接口
 * @author: Overload
 * @date: 2018/5/14 16:43
 * @version: 1.0
 */
public interface InfluxdbWriteService {
    /**
     * 将数据批量写入InfluxDB
     * @param payload 批量数据
     */
    void write(Payload payload);
}
