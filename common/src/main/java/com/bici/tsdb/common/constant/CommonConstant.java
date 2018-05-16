package com.bici.tsdb.common.constant;

/**
 * CommonConstant
 * @author: Overload
 * @date: 2018/5/14 18:47
 * @version: 1.0
 */
public class CommonConstant {

    /** 永久保存数据库名称前缀 */
    public final static String DATABASE_NO_RP_PREFIX = "fvrdb_";

    /** 限时保存数据库名称前缀 */
    public final static String DATABASE_WITH_RP_PREFIX = "lmtdb_";

    /** 表名称前缀 */
    public final static String MEASUREMENT_PREFIX = "mea_";

    /** 保存策略，5年 */
    public final static String RETENTION_POLICY_5_YEARS = "rp_5y";

    /** Kafka消息topic前缀，后面为工厂编号（数据库名称） */
    public final static String KAFKA_TOPIC_PREFIX = "dc.tsd.";

    /** redis中缓存错误数据列表名称 */
    public final static String REDIS_LIST_NAME = KAFKA_TOPIC_PREFIX + "errdata";
}
