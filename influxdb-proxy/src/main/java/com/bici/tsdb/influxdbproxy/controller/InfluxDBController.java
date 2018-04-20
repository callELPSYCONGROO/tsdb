package com.bici.tsdb.influxdbproxy.controller;

import com.bici.tsdb.common.constant.MeasurementEunm;
import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import com.bici.tsdb.common.util.JsonUtil;
import com.bici.tsdb.influxdbproxy.service.InfluxDBRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * InfluxDBController
 * @author: bici
 * @date: 2018/4/16 11:40
 * @version: 1.0
 */
@RestController
public class InfluxDBController {

    @Resource(name = "influxDBRepo")
    private InfluxDBRepo influxDBRepo;

    @RequestMapping(value = "/insertPoints", method = RequestMethod.POST)
    public void insertPoints(@RequestParam("data") String data) throws InfluxBusinessException {
        PointDTO pointDTO = JsonUtil.json2Obj(data, PointDTO.class);
        influxDBRepo.insertPoints(pointDTO);
    }

    @RequestMapping(value = "/deleteDatabase", method = RequestMethod.POST)
    public void deleteDatabase(@RequestParam("database") String database) throws InfluxBusinessException {
        influxDBRepo.deleteDatabase(database);
    }

    @RequestMapping(value = "/createDatabase", method = RequestMethod.POST)
    public void createDatabase(@RequestParam("database") String database) throws InfluxBusinessException {
        influxDBRepo.createDatabase(database);
    }

    @RequestMapping(value = "/queryByTime", method = RequestMethod.POST)
    public List queryByTime(@RequestBody QueryObj queryObj) throws Exception {
        Class clsByMeasurement = MeasurementEunm.getClsByMeasurement(queryObj.getMeasurement());
        return influxDBRepo.queryByTime(queryObj, clsByMeasurement);
    }
}
