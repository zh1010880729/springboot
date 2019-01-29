package cn.linkedcare;

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


    @Test
    public void doTest() {
        System.out.println(redisService.setNX("demo", "张恒", 10000l));
        System.out.println(redisService.setNX("demo", "Benji", 10000l));
    }

}
