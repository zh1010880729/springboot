package cn.linkedcare.util;

import cn.linkedcare.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2018/8/21.
 */
@Component("lockUtil")
public class LockUtil {

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String TIME_UNIT_SECOND = "EX";
    private static final Long EXPIRE_TIME = 100l;


    @Autowired
    private RedisService redisService;

    public Boolean lock(String key, String value) {
        String result = redisService.set(key, value, SET_IF_NOT_EXIST, TIME_UNIT_SECOND, 100l);
        return StringUtils.isNotBlank(result) && result.equalsIgnoreCase("ok") ? true : false;
    }


}
