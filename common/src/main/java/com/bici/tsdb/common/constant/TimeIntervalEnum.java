package com.bici.tsdb.common.constant;

import java.util.concurrent.TimeUnit;

/**
 * TimeIntervalEnum
 * @author: Overload
 * @date: 2018/5/15 9:52
 * @version: 1.0
 */
public enum TimeIntervalEnum {

    /** 时间间隔，日 */
    INTERVAL_DAY("d", TimeUnit.DAYS),

    /** 时间间隔，时 */
    INTERVAL_HOUR("h", TimeUnit.HOURS),

    /** 时间间隔，分 */
    INTERVAL_MINUTE("m", TimeUnit.MINUTES),

    /** 时间间隔，秒 */
    INTERVAL_SECOND("s", TimeUnit.SECONDS),

    /** 时间间隔，毫秒 */
    INTERVAL_MILLISECOND("ms", TimeUnit.MILLISECONDS),

    /** 时间间隔，微秒 */
    INTERVAL_MICROSECOND("u", TimeUnit.MICROSECONDS),

    /** 时间间隔，纳秒 */
    INTERVAL_NANOSECOND("n", TimeUnit.NANOSECONDS);

    private String intervalTime;

    private TimeUnit timeUnit;
    
    TimeIntervalEnum(String intervalTime, TimeUnit timeUnit) {
        this.intervalTime = intervalTime;
        this.timeUnit = timeUnit;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * 根据字符串匹配对应的枚举类型
     * @param intervalTime 字符串
     * @return 枚举类型
     */
    public static TimeIntervalEnum getIntervalTimeEnum(String intervalTime) {
        for (TimeIntervalEnum timeIntervalEnum : TimeIntervalEnum.values()) {
            if (timeIntervalEnum.getIntervalTime().equals(intervalTime)) {
                return timeIntervalEnum;
            }
        }
        return null;
    }

    /**
     * 是否为枚举类型
     */
    public static boolean isIntervalTime(String intervalTime) {
        for (TimeIntervalEnum timeIntervalEnum : TimeIntervalEnum.values()) {
            if (timeIntervalEnum.getIntervalTime().equals(intervalTime)) {
                return true;
            }
        }
        return false;
    }
}
