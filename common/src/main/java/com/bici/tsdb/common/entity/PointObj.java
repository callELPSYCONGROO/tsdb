package com.bici.tsdb.common.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * 与InfluxDB.Point对象对应
 * @author: Overload
 * @date: 2018/4/13 16:18
 * @version: 1.0
 */
public class PointObj implements Serializable {
    /** 表名称 */
    private String measurement;
    /** 索引名 */
    private Map<String, String> tags;
    /** 时间戳，单位：纳秒 */
    private Long time;
    /** 值 */
    private Map<String, Object> fields;

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointObj pointObj = (PointObj) o;
        return Objects.equals(measurement, pointObj.measurement) && Objects.equals(tags, pointObj.tags) && Objects.equals(time, pointObj.time) && Objects.equals(fields, pointObj.fields);
    }

    @Override
    public int hashCode() {

        return Objects.hash(measurement, tags, time, fields);
    }

    @Override
    public String toString() {
        return "PointObj:{" + "measurement:'" + measurement + '\'' + ", tags:" + tags + ", time:" + time + ", fields:" + fields + '}';
    }
}
