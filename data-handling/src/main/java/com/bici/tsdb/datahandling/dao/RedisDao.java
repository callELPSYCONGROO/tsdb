package com.bici.tsdb.datahandling.dao;

/**
 * RedisDao
 * @author: Overload
 * @date: 2018/5/4 12:05
 * @version: 1.0
 */
public interface RedisDao {

    /**
     * 向redis中插入值
     * @param key key
     * @param value value
     * @param expire 持续时间，单位：秒
     */
    String set(String key, String value, int expire);

    String get(String key);

    Long del(String key);

    /**
     * 入队
     * @param listName 队列名称
     * @param value 入队值
     */
    Long push(String listName, String value);

    /**
     * 出队
     * @param listName 队列名称
     * @return 出队值
     */
    String pop(String listName);

}
