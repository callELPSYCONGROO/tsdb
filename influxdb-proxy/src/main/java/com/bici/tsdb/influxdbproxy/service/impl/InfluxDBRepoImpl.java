package com.bici.tsdb.influxdbproxy.service.impl;

import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.PointObj;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import com.bici.tsdb.common.util.StringUtil;
import com.bici.tsdb.influxdbproxy.service.InfluxDBRepo;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * InfluxDBRepoImpl
 * @author: bici
 * @date: 2018/4/13 14:52
 * @version: 1.0
 */
@Repository("influxDBRepo")
public class InfluxDBRepoImpl implements InfluxDBRepo {

    @Value("${influx.master.url}")
    private String masterUrl;

    @Value("${influx.slave.url}")
    private String slaveUrl;

    @Value("${influx.username.reader}")
    private String usernameR;

    @Value("${influx.password.reader}")
    private String passwordR;

    @Value("${influx.username.writer}")
    private String usernameW;

    @Value("${influx.password.writer}")
    private String passwordW;

    private final static String W = "W";

    private final static String R = "R";

    /**
     * 获取读连接对象，读写分离
     * @param type 连接类型，W-写操作，R-读操作
     */
    private InfluxDB getInflxDBConnect(String type) throws InfluxBusinessException {
        String username;
        String password;
        if (W.equals(type)) {
            username = usernameW;
            password = passwordW;
            return InfluxDBFactory.connect(masterUrl, username, password);
        } else if (R.equals(type)) {
            username = usernameR;
            password = passwordR;
            return InfluxDBFactory.connect(slaveUrl, username, password);
        } else {
            throw new InfluxBusinessException("type is wrong");
        }

    }

    @Override
    public void insertPoints(PointDTO pointDTO) throws InfluxBusinessException {
        BatchPoints.Builder builder = BatchPoints.database(pointDTO.getDatabase()).retentionPolicy(pointDTO.getRetentionPolicy());
        for (PointObj pointObj : pointDTO.getPointObjs()) {
            Point point = Point.measurement(pointObj.getMeasurement())
                    .tag(pointObj.getTags())
                    .fields(pointObj.getFields())
                    .time(pointObj.getTime(), TimeUnit.NANOSECONDS)
                    .build();
            builder.point(point);
        }
        InfluxDB inflxDBConnectWriter = this.getInflxDBConnect(W);
        inflxDBConnectWriter.write(builder.build());
        inflxDBConnectWriter.close();
    }

    @Override
    public void deleteDatabase(String database) throws InfluxBusinessException {
        InfluxDB inflxDBConnectWriter = this.getInflxDBConnect(W);
        QueryResult queryResult = inflxDBConnectWriter.query(new Query("DROP DATABASE " + database, null));
        inflxDBConnectWriter.close();
        if (queryResult.hasError()) {
            throw new InfluxBusinessException(queryResult.getError());
        }
    }

    @Override
    public void createDatabase(String database) throws InfluxBusinessException {
        InfluxDB inflxDBConnectWriter = this.getInflxDBConnect(W);
        QueryResult queryResult = inflxDBConnectWriter.query(new Query("CREATE DATABASE " + database, null));
        inflxDBConnectWriter.close();
        if (queryResult.hasError()) {
            throw new InfluxBusinessException(queryResult.getError());
        }
    }

    @Override
    public <DAO> List<DAO> queryByTime(QueryObj queryObj, Class<DAO> daoClass) throws InfluxBusinessException {
        InfluxDB inflxDBConnectReader = this.getInflxDBConnect(R);
        String command = "SELECT * FROM " + queryObj.getMeasurement();
        String orderBy = StringUtil.isBlank(queryObj.getOrderBy()) ? "" : (" ORDER BY " + queryObj.getOrderBy());
        String limit = queryObj.getLimit() == null || queryObj.getLimit() == 0 ? "" : (" LIMIT " + queryObj.getLimit());
        command += (orderBy + limit);
        QueryResult queryResult = inflxDBConnectReader.query(new Query(command, queryObj.getDatabase()));
        inflxDBConnectReader.close();
        if (queryResult.hasError()) {
            throw new InfluxBusinessException(queryResult.getError());
        }
        InfluxDBResultMapper influxDBResultMapper = new InfluxDBResultMapper();
        return influxDBResultMapper.toPOJO(queryResult, daoClass);
    }
}
