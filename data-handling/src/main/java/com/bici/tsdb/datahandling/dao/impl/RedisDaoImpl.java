package com.bici.tsdb.datahandling.dao.impl;

import com.bici.tsdb.datahandling.dao.RedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * RedisDaoImpl
 * @author: Overload
 * @date: 2018/5/4 17:13
 * @version: 1.0
 */
@Repository("redisDao")
public class RedisDaoImpl implements RedisDao {

    @Value("${jedis.password}")
    private String password;

    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    /**
     * 从连接池中获取链接
     * @return jedis连接
     */
    private Jedis getRedisResource() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(password);
        return jedis;
    }

    @Override
    public String set(String key, String value, int expire) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.set(key, value, "XX", "EX", expire);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.get(key);
        }
    }

    @Override
    public Long del(String key) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.del(key);
        }
    }

    @Override
    public Long push(String listName, String[] value) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.lpush(listName, value);
        }
    }

    @Override
    public Long push(byte[] listName, byte[][] value) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.lpush(listName, value);
        }
    }

    @Override
    public String pop(String listName) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.rpop(listName);
        }
    }

    @Override
    public byte[] pop(byte[] listName) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.rpop(listName);
        }
    }

    @Override
    public long llen(String listName) {
        try (Jedis jedis = this.getRedisResource()){
            return jedis.llen(listName);
        }
    }
}
