package com.bici.tsdb.query.controller;

import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.entity.ResultData;
import com.bici.tsdb.common.service.InfluxDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * QueryController
 * @author: bici
 * @date: 2018/4/16 10:02
 * @version: 1.0
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private InfluxDBRepo influxDBRepo;

    @RequestMapping("/byTime")
    public ResultData queryByTime(QueryObj queryObj) throws Exception {
        List list = influxDBRepo.queryByTime(queryObj);
        return ResultData.success(list);
    }
}
