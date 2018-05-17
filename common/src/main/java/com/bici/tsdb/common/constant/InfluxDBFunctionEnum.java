package com.bici.tsdb.common.constant;

/**
 * InfluxDBFunctionEnum
 * @author: Overload
 * @date: 2018/5/16 18:26
 * @version: 1.0
 */
public enum InfluxDBFunctionEnum {

    COUNT,
    SUM,
    MAX,
    MIN,
    MEAN,
    DISTINCT,
    SPREAD,
    MEDIAN,
    DERIVATIVE,
    DIFFERENCE,
    FIRST,
    LAST,
    PERCENTILE,
    ELAPSED,
    MOVING_AVERAGE,
    NON_NEGATIVE_DERIVATIVE,
    STDDEV;

    public static boolean isInfluxDBFunction(String funcName) {
        try {
            InfluxDBFunctionEnum.valueOf(funcName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
