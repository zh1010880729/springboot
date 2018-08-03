package cn.linkedcare.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Benji on 2018/8/2.
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut(value = "execution(* cn.linkedcare.controller.*.*(..))")
    public void point() {

    }

    @Before("point()")
    public void doBefore() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        System.out.println("Aop 开始执行");
        System.out.println("访问IP：" + remoteAddr);
    }
}
