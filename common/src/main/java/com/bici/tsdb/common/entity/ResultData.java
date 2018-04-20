package com.bici.tsdb.common.entity;

import java.io.Serializable;

/**
 * ResultData结果集
 * @author: Overload
 * @date: 2018/4/16 10:09
 * @version: 1.0
 */
public class ResultData implements Serializable {

    private Integer code;

    private String msg;

    private Object rd;

    public ResultData(Integer code, String msg, Object rd) {
        this.code = code;
        this.msg = msg;
        this.rd = rd;
    }

    public static ResultData success() {
        return success(null);
    }

    public static ResultData success(Object rd) {
        return new ResultData(100, "success", rd);
    }

    public static ResultData fail(Integer code, String msg) {
        return fail(code, msg, null);
    }

    public static ResultData fail(Integer code, String msg, Object rd) {
        return new ResultData(code, msg, rd);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRd() {
        return rd;
    }

    public void setRd(Object rd) {
        this.rd = rd;
    }

    @Override
    public String toString() {
        return "ResultData:{" + "code:" + code + ", msg:'" + msg + '\'' + ", rd:" + rd + '}';
    }
}
