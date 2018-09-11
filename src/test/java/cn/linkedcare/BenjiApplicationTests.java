package cn.linkedcare;

import cn.linkedcare.util.LockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BenjiApplication.class)
public class BenjiApplicationTests {

    @Autowired
    private LockUtil lockUtil;


    @Test
    public void doTest() {
        System.out.println(lockUtil.lock("lock", "benji"));
        System.out.println(lockUtil.lock("lock", "benji"));
    }

}
