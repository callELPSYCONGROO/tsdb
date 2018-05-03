package com.bici.tsdb.common.entity;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Cpu
 * @author: Overload
 * @date: 2018/5/3 9:05
 * @version: 1.0
 */
@Measurement(name = "cpu", timeUnit = TimeUnit.NANOSECONDS)
public class Cpu implements Serializable {
    @Column(name = "time")
    private Instant time;
    @Column(name = "host", tag = true)
    private String host;
    @Column(name = "region", tag = true)
    private String region;
    @Column(name = "value")
    private Double value;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cpu cpu = (Cpu) o;
        return Objects.equals(time, cpu.time) && Objects.equals(host, cpu.host) && Objects.equals(region, cpu.region) && Objects.equals(value, cpu.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, host, region, value);
    }
}
