package com.bici.tsdb.influxdbproxy.service;

import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.entity.SenorObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;

import java.util.List;

/**
 * 连接InfluxDB
 * @author: bici
 * @date: 2018/4/13 14:52
 * @version: 1.0
 */
public interface InfluxDBRepo {

    /**
     * 批量写入
     */
    void insertPoints(PointDTO pointDTO) throws InfluxBusinessException;

    /**
     * 删除数据库
     * @param database 数据库名称
     */
    void deleteDatabase(String database) throws InfluxBusinessException;

    /**
     * 创建数据库
     * @param database 数据库名称
     */
    void createDatabase(String database) throws InfluxBusinessException;

    /**
     * 按时间查询
     * @param queryObj 查询条件
     * @param daoClass 查询结果对象类型
     * @param <DAO> 查询结果类
     * @return 结果集
     */
    <DAO> List<DAO> queryByTime(QueryObj queryObj, Class<DAO> daoClass) throws InfluxBusinessException;

    void insertSenor(SenorObj senorObj) throws InfluxBusinessException;
}
