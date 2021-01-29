package com.leo;

import com.leo.dynamicproxy.UserService;
import com.leo.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyBeanProcessorTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext6.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.register();
    }
}
