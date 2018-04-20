package com.bici.tsdb.common.entity;

/**
 * DataMessage
 * @author: bici
 * @date: 2018/4/17 13:51
 * @version: 1.0
 */
public class DataMessage {

    private String t;
    private String h;
    private String r;
    private Float f;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Float getF() {
        return f;
    }

    public void setF(Float f) {
        this.f = f;
    }
}
