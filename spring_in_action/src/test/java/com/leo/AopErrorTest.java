package com.leo;

import com.leo.aspect.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopErrorTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext8.xml");
        UserService u= (UserService) ctx.getBean("userService");
        u.login();
    }
}
