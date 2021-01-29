package com.leo.aop.cglib;

import com.leo.dynamicproxy.UserService;
import com.leo.dynamicproxy.UserServiceImpl;
import com.leo.entity.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {
    public static void main(String[] args) {
        //创建原始对象
        UserService userService = new UserServiceImpl();
        /**
         * 通过cglib创建动态代理对象
         *  Proxy.newProxyInstance(classloader,interface,invocationhandler)
         *
         *  Enhancer.setClassLoader() 类加载
         *  Enhancer.setSuperClass() 父类
         *  Enhancer.setCallback();  ---> MethodInterceptor(cglib) 设置额外功能
         *  Enhancer.create() ---> 代理
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(UserService.class.getClassLoader());
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("===cglib===");
                        return method.invoke(userService,objects);
                    }
                }
        );

        UserService userS = (UserService) enhancer.create();
        userS.register();
    }
}
