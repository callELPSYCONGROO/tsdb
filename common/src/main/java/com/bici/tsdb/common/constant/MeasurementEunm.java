package com.bici.tsdb.common.constant;

import com.bici.tsdb.common.entity.Cpu;
import com.bici.tsdb.common.entity.Student;

/**
 * MeasurementEunm表与实例对象枚举类
 * @author: Overload
 * @date: 2018/4/16 10:13
 * @version: 1.0
 */
public enum MeasurementEunm {

    STUDENT("student", Student.class),
    CESHI("cpu", Cpu.class);

    /** 表名 */
    private String measurement;

    /** 对象类 */
    private Class cls;

    MeasurementEunm(String measurement, Class cls) {
        this.measurement = measurement;
        this.cls = cls;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public static Class getClsByMeasurement(String measurement) throws Exception {
        for (MeasurementEunm m : MeasurementEunm.values()) {
            if (m.getMeasurement().equals(measurement)) {
                return m.getCls();
            }
        }
        throw new Exception("表名有误");
    }

    public static String getMeasurementByCls(Class cls) throws Exception {
        for (MeasurementEunm m : MeasurementEunm.values()) {
            if (m.getCls().equals(cls)) {
                return m.getMeasurement();
            }
        }
        throw new Exception("类型有误");
    }
}
