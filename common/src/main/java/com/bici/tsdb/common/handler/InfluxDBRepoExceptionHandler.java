package com.bici.tsdb.common.handler;

import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import com.bici.tsdb.common.service.InfluxDBRepo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * InfluxDBRepoExceptionHandler熔断处理
 * @author: bici
 * @date: 2018/4/16 9:54
 * @version: 1.0
 */
@Component("influxDBRepoExceptionHandler")
public class InfluxDBRepoExceptionHandler implements InfluxDBRepo {
    @Override
    public void insertPoints(String data) throws InfluxBusinessException {
        throw this.returnException();
    }

    @Override
    public void deleteDatabase(String database) throws InfluxBusinessException {
        throw this.returnException();
    }

    @Override
    public void createDatabase(String database) throws InfluxBusinessException {
        throw this.returnException();
    }

    @Override
    public List queryByTime(QueryObj queryObj) throws InfluxBusinessException {
        throw this.returnException();
    }

    private InfluxBusinessException returnException() {
        return new InfluxBusinessException("调用远程接口处理异常");
    }
}
