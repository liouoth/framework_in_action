package com.leo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {
    //切入点复用
    @Pointcut("execution(* *(..))")
    public void pointcut(){}
    //ProceedingJoinPoint 原始类中的方法
//    @Around("execution(* login(..))") //切面
    @Around(value = "pointcut()") //切面
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //额外功能
        System.out.println("-----log-----");
        Object result = pjp.proceed();
        return result;
    }
}
