package cn.linkedcare.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Created by Benji on 2018/6/22.
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private String topic;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    private MessageListener messageListener;

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic(topic);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListener, channelTopic());
        return container;
    }

}
