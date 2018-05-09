package com.bici.tsdb.common.entity;

import java.util.Objects;

/**
 * Message
 * @author: Overload
 * @date: 2018/5/8 10:56
 * @version: 1.0
 */
public class Message {

    private Integer t;

    private Double v;

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Message message = (Message) o;
        return Objects.equals(t, message.t) && Objects.equals(v, message.v);
    }

    @Override
    public int hashCode() {

        return Objects.hash(t, v);
    }
}
