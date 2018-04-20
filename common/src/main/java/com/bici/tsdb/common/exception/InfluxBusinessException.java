package com.bici.tsdb.common.exception;

/**
 * InfluxBusinessException业务异常
 * @author: bici
 * @date: 2018/4/13 16:50
 * @version: 1.0
 */
public class InfluxBusinessException extends Exception {

    public InfluxBusinessException(String msg) {
        super(msg);
    }
}
