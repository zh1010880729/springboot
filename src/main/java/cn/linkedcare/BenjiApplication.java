package cn.linkedcare;

import cn.linkedcare.entity.JedisConsumer;
import cn.linkedcare.service.RedisService;
import cn.linkedcare.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/*@EnableApolloConfig*/
@SpringBootApplication
@EnableScheduling
@ImportResource(locations = {"classpath:transaction.xml"})
@MapperScan("cn.linkedcare.dao")
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BenjiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenjiApplication.class, args);
        RedisService redisService = SpringContextUtil.getBean(RedisService.class);
        redisService.subscibe("benji", JedisConsumer.class);
    }
}
