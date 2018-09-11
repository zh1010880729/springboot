package cn.linkedcare.service.impl;

import cn.linkedcare.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Created by Benji on 2018/8/4.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, int expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public String set(String key, String value, String model, String timeUnit, Long expireTime) {
        JedisConnection connection = (JedisConnection) jedisConnectionFactory.getConnection();
        Jedis jedis = connection.getJedis();
        return jedis.set(key, value, model, timeUnit, expireTime);
    }

    @Override
    public String get(String username) {
        return redisTemplate.opsForValue().get(username);
    }
}
