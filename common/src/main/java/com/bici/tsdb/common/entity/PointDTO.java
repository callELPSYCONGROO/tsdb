package com.bici.tsdb.common.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * PointDTO数据组
 * @author: Overload
 * @date: 2018/4/16 11:55
 * @version: 1.0
 */
public class PointDTO implements Serializable {

    private String database;

    private String retentionPolicy;

    private List<PointObj> pointObjs;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getRetentionPolicy() {
        return retentionPolicy;
    }

    public void setRetentionPolicy(String retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
    }

    public List<PointObj> getPointObjs() {
        return pointObjs;
    }

    public void setPointObjs(List<PointObj> pointObjs) {
        this.pointObjs = pointObjs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointDTO pointDTO = (PointDTO) o;
        return Objects.equals(database, pointDTO.database) && Objects.equals(retentionPolicy, pointDTO.retentionPolicy) && Objects.equals(pointObjs, pointDTO.pointObjs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(database, retentionPolicy, pointObjs);
    }

    @Override
    public String toString() {
        return "PointDTO:{" + "database:'" + database + '\'' + ", retentionPolicy:'" + retentionPolicy + '\'' + ", pointObjs:" + pointObjs + '}';
    }
}
