package com.bici.tsdb.datahandling.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisConfig
 * @author: Overload
 * @date: 2018/5/4 12:07
 * @version: 1.0
 */
@Configuration
public class JedisConfig {

    @Value("${jedis.host}")
    private String host;

    @Value("${jedis.port}")
    private int port;

    @Value("${jedis.database}")
    private int database;

    @Value("${jedis.timeout}")
    private int timeout;

    @Value("${jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${jedis.pool.min-idle}")
    private int minIdle;

    @Value("${jedis.pool.max-active}")
    private int maxActive;

    @Value("${jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean(name = "jedisPool")
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
}
