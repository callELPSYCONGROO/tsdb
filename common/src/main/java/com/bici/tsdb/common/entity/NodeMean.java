package com.bici.tsdb.common.entity;

import com.bici.tsdb.common.constant.CommonConstant;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * NodeMean
 * @author: Overload
 * @date: 2018/5/16 15:43
 * @version: 1.0
 */
@Measurement(name = CommonConstant.MEASUREMENT_PREFIX)
public class NodeMean {

    @Column(name = "mean")
    private String value;

    @Column(name = "time")
    private Instant time;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return String.valueOf(this.time.toEpochMilli());
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
