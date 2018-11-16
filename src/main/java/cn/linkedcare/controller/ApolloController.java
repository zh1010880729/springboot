package cn.linkedcare.controller;

import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.enumeration.HttpCode;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Benji on 2018/6/14.啦啦啦
 */
@RestController
@RequestMapping("apollo")
public class ApolloController {

    @ApolloConfig
    private Config config;

    @RequestMapping(value = "{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResultMap getApolloProperties(@PathVariable("key") String key) {
        return CommonResultMap.builder(HttpCode.OK).msg(config.getProperty(key, "这是一个默认值")).build();
    }

}
