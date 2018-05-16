package com.bici.tsdb.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * SensorData传感器数据
 * @author: Overload
 * @date: 2018/5/14 16:33
 * @version: 1.0
 */
@Data
public class SensorData implements Serializable {

    /** 传感器值，对应point的field */
    @JSONField(name = "vl")
    private Float value;

    /** 时间戳 */
    @JSONField(name = "t")
    private Long time;

    /** 传感器类型，数据库tag标签 */
    @JSONField(name = "dt")
    private String dataType;

    public String kvDataType() {
        return "dataType=" + this.dataType;
    }

    public String kvValue() {
        return "value=" + this.value;
    }
}
