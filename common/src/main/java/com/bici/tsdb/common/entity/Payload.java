package com.bici.tsdb.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Payload接收器
 * @author: Overload
 * @date: 2018/5/14 16:36
 * @version: 1.0
 */
@Data
public class Payload implements Serializable {

    /** 工厂编号，对应数据库名称 */
    @JSONField(name = "fn")
    private String factoryNo;

    /** 接收器设备号，对应表名称 */
    @JSONField(name = "dn")
    private String deviceNo;

    /** 传感器数据 */
    @JSONField(name = "dts")
    private List<SensorData> datas;

    public void replaceString() {
        this.setFactoryNo(this.getFactoryNo().replaceAll("\\W", ""));
        this.setDeviceNo(this.getDeviceNo().replaceAll("\\W", ""));
    }
}
