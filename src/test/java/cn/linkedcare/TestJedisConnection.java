package cn.linkedcare;

import cn.linkedcare.config.properties.RedisConfigProperties;
import cn.linkedcare.entity.JedisConsumer;
import cn.linkedcare.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BenjiApplication.class)
public class TestJedisConnection {

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisConfigProperties redisConfigProperties;

    @Test
    public void doTest() {
        redisService.publish(redisConfigProperties.getTopic(), "测试啊啊啊啊！");
    }

}
