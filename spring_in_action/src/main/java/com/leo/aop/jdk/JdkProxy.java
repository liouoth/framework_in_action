package com.leo.aop.jdk;

import com.leo.dynamicproxy.UserService;
import com.leo.dynamicproxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String[] args) {
        //创建原始类，工厂中可以通过全限定名反射获取对象
        UserService userService = new UserServiceImpl();

        //jdk创建动态代理,为原始类添加额外功能
        UserService userProxy = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        return method.invoke(userService, args);
                    }
                });
        userProxy.register();
    }
}
