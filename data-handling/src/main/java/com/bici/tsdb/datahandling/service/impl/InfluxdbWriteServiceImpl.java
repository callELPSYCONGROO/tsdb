package com.bici.tsdb.datahandling.service.impl;

import com.bici.tsdb.common.constant.CommonConstant;
import com.bici.tsdb.common.entity.Payload;
import com.bici.tsdb.common.entity.SensorData;
import com.bici.tsdb.datahandling.service.InfluxdbWriteService;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * InfluxDB写入接口
 * @author: Overload
 * @date: 2018/5/14 16:43
 * @version: 1.0
 */
@Service("influxdbWriteService")
public class InfluxdbWriteServiceImpl implements InfluxdbWriteService {

    @Value("${influx.url}")
    private String url;

    @Value("${influx.username}")
    private String username;

    @Value("${influx.password}")
    private String password;

    /**
     * 建立连接对象
     */
    private InfluxDB influxDBConnect() {
        return InfluxDBFactory.connect(url, username, password);
    }

    @Override
    public void write(Payload payload) {
        String database = CommonConstant.DATABASE_WITH_RP_PREFIX + payload.getFactoryNo();
        String measurement = CommonConstant.MEASUREMENT_PREFIX + payload.getDeviceNo();
        BatchPoints.Builder builder = BatchPoints.database(database)// 设置数据库
                .retentionPolicy(CommonConstant.RETENTION_POLICY_5_YEARS);// 设置保存策略，默认五年
        for (SensorData sensorData : payload.getDatas()) {
            Point point = Point.measurement(measurement)// 设置表名称
                    .time(sensorData.getTime(), TimeUnit.MILLISECONDS)// 设置时间戳
                    .tag("dataType", sensorData.getDataType())// 设置标签
                    .addField("value", sensorData.getValue())// 设置域值
                    .build();
            builder.point(point);
        }
        this.influxDBConnect()
                .setDatabase(database)// 设置数据库
                .write(builder.build());// 写数据
    }
}
