package com.bici.tsdb.common.entity;

import com.bici.tsdb.common.constant.CommonConstant;
import com.bici.tsdb.common.constant.NodeValueEnum;
import com.bici.tsdb.common.constant.TimeIntervalEnum;
import lombok.Data;

/**
 * QueryObj查询参数对象
 * @author: Overload
 * @date: 2018/5/15 8:42
 * @version: 1.0
 */
@Data
public class QueryObj {

    /** 开始时间，毫秒 */
    private Long startTime;

    /** 结束时间，毫秒 */
    private Long endTime;

    /** 标签 */
    private String tag;

    /** 数据库名称 */
    private String database;

    /** 表名称 */
    private String measurement;

    /** 时间间隔类型，默认为秒 */
    private String intervalType = "s";

    /** 查询限制条数 */
    private Integer limit;

    /** 时间周期 */
    private String period;

    /** 聚合类型，值处理方式 */
    private String valueDealType;

    public String getStartTimeSql() {
        return this.startTime == null ? "" : "AND time > " + this.startTime;
    }

    public String getEndTimeSql() {
        return this.endTime == null ? "" : "AND time < " + this.endTime;
    }

    public String getLimitSql() {
        return this.limit == null || this.limit <= 0 ? "" : "LIMIT " + this.limit;
    }

    public String getPeriodSql() {
        return this.period == null ? "" : "GROUP BY time(" + this.period + ")";
    }

    public String getValueDealTypeSql() {
        return NodeValueEnum.isNodeValueEnum(this.valueDealType) ? this.valueDealType + "(value)" : "*";
    }

    public String getMeasurementSql() {
        return this.measurement == null ? "" : CommonConstant.MEASUREMENT_PREFIX + this.measurement;
    }

    public String getDatabaseSql() {
        return CommonConstant.DATABASE_WITH_RP_PREFIX + this.database;
    }

    public void replaceString() throws Exception {
        this.setDatabase(this.getDatabase().replaceAll("\\W", ""));
        this.setMeasurement(this.getMeasurement().replaceAll("\\W", ""));
        if (this.getStartTime() == null && this.getEndTime() != null) {
            throw new Exception("开始时间为空");
        }
        if (this.getDatabase() == null
                || this.getMeasurement() == null
                || !TimeIntervalEnum.isIntervalTime(this.getIntervalType())) {
            throw new Exception("必要参数为空");
        }
        // 聚合周期和数据处理方式必须同时有值或者无值
        if (!((this.getPeriod() != null && this.getValueDealType() != null) || (this.getPeriod() == null && this.getValueDealType() == null))) {
            throw new Exception("聚合方式错误");
        }
    }

    public String getTagSql() {
        return this.tag == null ? "" : "dataType='" + this.tag + "'";
    }
}
