package com.leo;

import com.leo.aspect.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class annotationAopTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext7.xml");
        UserService userS = (UserService) ctx.getBean("userService");
        userS.login();
    }
}
