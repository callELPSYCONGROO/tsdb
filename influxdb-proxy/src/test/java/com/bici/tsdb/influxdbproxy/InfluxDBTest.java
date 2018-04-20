package com.bici.tsdb.influxdbproxy;

import com.bici.tsdb.influxdbproxy.service.InfluxDBRepo;
import com.bici.tsdb.common.entity.PointObj;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.entity.Student;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InfluxDBTest
 * @author: bici
 * @date: 2018/4/13 17:42
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfluxDBTest {

    @Autowired
    private InfluxDBRepo influxDBRepo;

    @Test
    public void deleteDatabaseTest() throws InfluxBusinessException {
        influxDBRepo.deleteDatabase("ceshi");
    }

    @Test
    public void createDatabaseTest() throws InfluxBusinessException {
        influxDBRepo.createDatabase("nima");
    }

    @Test
    public void insertPointsTest() throws InfluxBusinessException {
        List<PointObj> pointObjs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PointObj pointObj = new PointObj();
            pointObj.setMeasurement("student");
            pointObj.setTime(System.nanoTime() + i + 10);
            Map<String, String> tags = new HashMap<>();
            tags.put("sex", i % 2 == 0 ? "F" : "M");
            tags.put("class", i % 3 == 0 ? "middle" : "high");
            pointObj.setTags(tags);
            Map<String, Object> fields = new HashMap<>();
            fields.put("name", String.valueOf(Character.valueOf((char) i)));
            fields.put("age", i + 20);
            pointObj.setFields(fields);
            pointObjs.add(pointObj);
        }
//        influxDBRepo.insertPoints("ceshi", null, pointObjs);
    }

    @Test
    public void queryByTimeTest() throws InfluxBusinessException {
        QueryObj baseBean = new QueryObj();
        baseBean.setDatabase("ceshi");
        baseBean.setMeasurement("student");
        baseBean.setStartTime(System.nanoTime() - 1000000000);
        baseBean.setEndTime(System.nanoTime());
        List<Student> baseBeans = influxDBRepo.queryByTime(baseBean, Student.class);
        System.out.println("*****************************\n" + baseBeans + "\n*****************************");
    }
}
