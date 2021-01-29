package com.leo.dynamicproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class FuncInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("调用之前");
//        Object result = methodInvocation.getMethod().invoke(methodInvocation.getThis(),methodInvocation.getArguments());
        Object result  = methodInvocation.proceed();//直接执行
        System.out.println("调用之后");
        return result;
    }
}
