package com.bici.tsdb.common.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * QueryObj查询对象
 * @author: Overload
 * @date: 2018/4/13 17:01
 * @version: 1.0
 */
public class QueryObj implements Serializable {

    private String database;

    private String measurement;

    private Long startTime;

    private Long endTime;

    private Integer limit;

    private String orderBy;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        QueryObj queryObj = (QueryObj) o;
        return Objects.equals(database, queryObj.database) && Objects.equals(measurement, queryObj.measurement) && Objects.equals(startTime, queryObj.startTime) && Objects.equals(endTime, queryObj.endTime) && Objects.equals(limit, queryObj.limit) && Objects.equals(orderBy, queryObj.orderBy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(database, measurement, startTime, endTime, limit, orderBy);
    }

    @Override
    public String toString() {
        return "QueryObj:{" + "database:'" + database + '\'' + ", measurement:'" + measurement + '\'' + ", startTime:" + startTime + ", endTime:" + endTime + ", limit:" + limit + ", orderBy:'" + orderBy + '\'' + '}';
    }
}
