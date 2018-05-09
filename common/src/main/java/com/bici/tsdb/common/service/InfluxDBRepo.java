package com.bici.tsdb.common.service;

import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import com.bici.tsdb.common.handler.InfluxDBRepoExceptionHandler;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 连接InfluxDB
 * @author: bici
 * @date: 2018/4/13 14:52
 * @version: 1.0
 */
@Service
@FeignClient(value = "influxdbproxy", fallback = InfluxDBRepoExceptionHandler.class)
public interface InfluxDBRepo {
    /**
     * 批量写入
     * @param data 数据Json字符串
     */
    @RequestMapping(value = "/insertPoints", method = RequestMethod.POST)
    void insertPoints(@RequestParam("data") String data) throws InfluxBusinessException;

    /**
     * 删除数据库
     * @param database 数据库名称
     */
    @RequestMapping(value = "/deleteDatabase", method = RequestMethod.POST)
    void deleteDatabase(@RequestParam("database") String database) throws InfluxBusinessException;

    /**
     * 创建数据库
     * @param database 数据库名称
     */
    @RequestMapping(value = "/createDatabase", method = RequestMethod.POST)
    void createDatabase(@RequestParam("database") String database) throws InfluxBusinessException;

    /**
     * 按时间查询
     * @param queryObj 查询条件
     * @return 结果集
     */
    @RequestMapping(value = "/queryByTime", method = RequestMethod.POST)
    List queryByTime(@RequestBody QueryObj queryObj) throws InfluxBusinessException;

    /**
     * 接受报文
     */
    @RequestMapping(value = "/insertSenor", method = RequestMethod.POST)
    void insertSenor(@RequestParam("data") String data) throws InfluxBusinessException;
}
