package com.bici.tsdb.querydata.service.impl;

import com.bici.tsdb.common.constant.CommonConstant;
import com.bici.tsdb.common.constant.NodeValueEnum;
import com.bici.tsdb.common.constant.TimeIntervalEnum;
import com.bici.tsdb.common.entity.QueryObj;
import com.bici.tsdb.common.util.InfluxDBResultMapper;
import com.bici.tsdb.common.util.StringUtil;
import com.bici.tsdb.querydata.service.InfluxdbReadService;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * InfluxDB读取接口
 * @author: Overload
 * @date: 2018/5/14 16:43
 * @version: 1.0
 */
@Service("influxdbReadService")
public class InfluxdbReadServiceImpl implements InfluxdbReadService {

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
    public List selectMeanByTime(QueryObj queryObj) {
        String sql = MessageFormat.format("SELECT {0} FROM {1}.{2}.{3} WHERE {4} {5} {6} {7} {8}",
                                            queryObj.getValueDealTypeSql(),
                                            queryObj.getDatabaseSql(),
                                            CommonConstant.RETENTION_POLICY_5_YEARS,
                                            queryObj.getMeasurementSql(),
                                            queryObj.getTagSql(),
                                            checkNull(queryObj.getStartTimeSql()),
                                            checkNull(queryObj.getEndTimeSql()),
                                            checkNull(queryObj.getPeriodSql()),
                                            checkNull(queryObj.getLimitSql()));
        System.out.println(sql);
        QueryResult queryResult = this.influxDBConnect().query(new Query(sql, queryObj.getDatabase()), Objects.requireNonNull(TimeIntervalEnum.getIntervalTimeEnum(queryObj.getIntervalType())).getTimeUnit());
        return new InfluxDBResultMapper().toPOJO(queryResult, NodeValueEnum.getNodeObj(queryObj.getValueDealType()));
    }

    /**
     * 空字符串返回空串
     */
    private String checkNull(String string) {
        return StringUtil.isBlank(string) ? "" : string;
    }
}
