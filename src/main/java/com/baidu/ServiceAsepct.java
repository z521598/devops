package com.baidu;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceAsepct {

    /* 定义一个切入点 */
    @Pointcut("execution(* com.baidu.service.*(..))")
    public void doPointCut() {

    }

    @Around("execution(* com.baidu.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long time = System.currentTimeMillis();
        System.out.println(time);
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            throw e;
        }finally{

        }
    }

}