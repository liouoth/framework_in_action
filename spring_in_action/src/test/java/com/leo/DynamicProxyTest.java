package com.leo;

import com.leo.dynamicproxy.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext3.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.login();
    }
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext4.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.login();
    }

    //注解切面

    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext5.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.login();
        userService.register();
    }
}
