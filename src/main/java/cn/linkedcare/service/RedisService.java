package cn.linkedcare.service;

/**
 * Created by Benji on 2018/8/4.
 */
public interface RedisService {

    void set(String key, String value);

    /**
     * @param key
     * @param value
     * @param expireTime 过期时间  单位：秒
     */
    void set(String key, String value, int expireTime);

    String get(String username);
}
