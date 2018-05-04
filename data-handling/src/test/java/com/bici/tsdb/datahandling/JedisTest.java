package com.bici.tsdb.datahandling;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisTest
 * @author: Overload
 * @date: 2018/5/4 17:33
 * @version: 1.0
 */
public class JedisTest {

    private Jedis jedis;

    @Before
    public void before() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setMaxTotal(10);
        jedis = (new JedisPool(jedisPoolConfig, "118.24.160.252", 6379, 3000)).getResource();

        jedis.auth("root");
    }

    @Test
    public void jedis() {
        jedis.set("asd", "dsa12rf23");
        String asd = jedis.get("asd");
        System.out.println(asd);
    }

    @After
    public void after() {
        jedis.close();
    }
}
