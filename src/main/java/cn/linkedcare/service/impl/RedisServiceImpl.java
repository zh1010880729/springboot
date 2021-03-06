package cn.linkedcare.service.impl;

import cn.linkedcare.config.properties.RedisConfigProperties;
import cn.linkedcare.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Benji on 2018/8/4.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private RedisConfigProperties redisConfigProperties;


    @Override
    public void set(String key, String value) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            jedis.set(key, value);
        } finally {
            releaseConnection(jedis);
        }
    }

    @Override
    public void set(String key, String value, int expireTime) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            if (successResponse(jedis.set(key, value))) {
                jedis.expire(key, expireTime);
            }
        } finally {
            releaseConnection(jedis);
        }
    }

    @Override
    public Boolean setNX(String key, String value, Long expireTime) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            return successResponse(jedis.set(key, value, "NX", "PX", expireTime));
        } finally {
            releaseConnection(jedis);
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            return jedis.get(key);
        } finally {
            releaseConnection(jedis);
        }
    }

    @Override
    public void subscibe(String chanel, Class<? extends JedisPubSub> jedisPubSub) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            JedisPubSub jedisPubSubInstance = jedisPubSub.newInstance();
            jedis.subscribe(jedisPubSubInstance, redisConfigProperties.getTopic());
        } catch (Exception e) {

        } finally {
            releaseConnection(jedis);
        }
    }

    @Override
    public void publish(String channel, String message) {
        Jedis jedis = getConnectionFromPool(jedisPool);
        try {
            jedis.publish(channel, message);
        } finally {
            releaseConnection(jedis);
        }
    }

    private boolean successResponse(String result) {
        if (StringUtils.isNotBlank(result) && result.equalsIgnoreCase("ok")) {
            return true;
        }
        return false;
    }


    private Jedis getConnectionFromPool(JedisPool jedisPool) {
        return jedisPool.getResource();
    }

    private void releaseConnection(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
