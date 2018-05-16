package com.bici.tsdb.querydata.controller;

import com.bici.tsdb.common.constant.TimeIntervalEnum;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.entity.ResultDTO;
import com.bici.tsdb.querydata.service.InfluxdbReadService;
import org.influxdb.InfluxDBException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * QueryController
 * @author: Overload
 * @date: 2018/5/15 11:43
 * @version: 1.0
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Resource(name = "influxdbReadService")
    private InfluxdbReadService influxdbReadService;

//    @RequestMapping(value = "/meanByTime", method = {RequestMethod.GET, RequestMethod.POST}, headers = "content-type=application/json")
    @RequestMapping(value = "/meanByTime")
    public ResultDTO meanByTime(QueryObj queryObj) {
        try {
            queryObj.replaceString();// 整理表库名称格式
            return ResultDTO.success(influxdbReadService.selectMeanByTime(queryObj));
        } catch (InfluxDBException e) {
            e.printStackTrace();
            return ResultDTO.fail(102, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        return ResultDTO.fail(99, e.getMessage());
        }
    }
}
