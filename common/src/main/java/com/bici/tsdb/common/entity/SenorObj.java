package com.bici.tsdb.common.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * SenorObj
 * @author: Overload
 * @date: 2018/5/8 10:55
 * @version: 1.0
 */
public class SenorObj implements Serializable {

    private String v;

    private String dn;

    private Integer mbs;

    private Integer ss;

    private List<Message> ds;

    private Long t;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public Integer getMbs() {
        return mbs;
    }

    public void setMbs(Integer mbs) {
        this.mbs = mbs;
    }

    public Integer getSs() {
        return ss;
    }

    public void setSs(Integer ss) {
        this.ss = ss;
    }

    public List<Message> getDs() {
        return ds;
    }

    public void setDs(List<Message> ds) {
        this.ds = ds;
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SenorObj senorObj = (SenorObj) o;
        return Objects.equals(v, senorObj.v) && Objects.equals(dn, senorObj.dn) && Objects.equals(mbs, senorObj.mbs) && Objects.equals(ss, senorObj.ss) && Objects.equals(ds, senorObj.ds) && Objects.equals(t, senorObj.t);
    }

    @Override
    public int hashCode() {

        return Objects.hash(v, dn, mbs, ss, ds, t);
    }
}
