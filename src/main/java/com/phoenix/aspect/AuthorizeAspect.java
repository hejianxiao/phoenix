package com.phoenix.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hjx
 * 2018/2/1 0001.
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {

    @Pointcut("execution(public * com.phoenix.controller.*.*.*(..))")
    public void verify() {}

    @After("verify()")
    public void doVerifyAfter() {
    }
}
