package cn.linkedcare.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2018/8/4.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }


    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }


    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
