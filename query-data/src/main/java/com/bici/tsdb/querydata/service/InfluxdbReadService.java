package com.bici.tsdb.querydata.service;

import com.bici.tsdb.common.entity.QueryObj;

import java.util.List;

/**
 * InfluxDB读取接口
 * @author: Overload
 * @date: 2018/5/14 16:43
 * @version: 1.0
 */
public interface InfluxdbReadService {

    /**
     * 按月分隔，获取时间间隔内的平均数据
     * @param queryObj 查询对象
     * @return 结果集
     */
    List selectMeanByTime(QueryObj queryObj);
}
