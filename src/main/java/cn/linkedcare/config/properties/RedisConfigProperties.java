package cn.linkedcare.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2019/1/29.
 */
@Data
@Component("redisConfigProperties")
@ConfigurationProperties("spring.redis")
public class RedisConfigProperties {
    private String host;
    private Integer port;
    private String password;
    private String database;
    private Integer timeout;
    private Integer maxIdle;
    private Integer maxTotal;
    private Long maxWaitMillis;
    private Boolean testOnBorrow;
    private Boolean blockWhenExhausted;
    private String topic;
}
