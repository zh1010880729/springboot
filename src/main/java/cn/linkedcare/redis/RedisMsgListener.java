package cn.linkedcare.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2018/6/22.
 */
@Component("messageListener")
public class RedisMsgListener implements MessageListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String msg = String.valueOf(redisTemplate.getValueSerializer().deserialize(message.getBody()));
        System.out.println("我已收到队列上的信息--->" + msg);
    }
}
