package cn.linkedcare.util;

import cn.linkedcare.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2018/8/21.
 */
@Component("lockUtil")
public class LockUtil {

    private static final Long EXPIRE_TIME = 10000L;

    @Autowired
    private RedisService redisService;

    public Boolean lock(String key, String value) {
        return redisService.setNX(key, value, EXPIRE_TIME);
    }


}
