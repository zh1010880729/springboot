package cn.linkedcare.config;

import cn.linkedcare.config.properties.RedisConfigProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Benji on 2018/6/22.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    @Bean
    public JedisPool getJedisPool() {
        return new JedisPool(getGenericObjectPoolConfig(), redisConfigProperties.getHost(), redisConfigProperties.getPort(), redisConfigProperties.getTimeout(), redisConfigProperties.getPassword());
    }

    @Bean
    public Jedis getJedis() {
        return getJedisPool().getResource();
    }


    private GenericObjectPoolConfig getGenericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestOnBorrow(redisConfigProperties.getTestOnBorrow());
        poolConfig.setBlockWhenExhausted(redisConfigProperties.getBlockWhenExhausted());
        poolConfig.setMaxIdle(redisConfigProperties.getMaxIdle());
        poolConfig.setMaxTotal(redisConfigProperties.getMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfigProperties.getMaxWaitMillis());
        return poolConfig;
    }
}
