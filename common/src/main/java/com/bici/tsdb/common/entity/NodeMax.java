package com.bici.tsdb.common.entity;

import com.bici.tsdb.common.constant.CommonConstant;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;
import java.time.Instant;

/**
 * Node传感器储存的一条记录节点
 * @author: Overload
 * @date: 2018/5/15 8:51
 * @version: 1.0
 */
@Measurement(name = CommonConstant.MEASUREMENT_PREFIX)
public class NodeMax implements Serializable {

    @Column(name = "max")
    private Double value;

    @Column(name = "time")
    private Instant time;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTime() {
        return String.valueOf(this.time.toEpochMilli());
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
