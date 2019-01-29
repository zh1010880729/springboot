package cn.linkedcare.entity;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by Benji on 2019/1/29.
 */
public class JedisConsumer extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
    }
}
