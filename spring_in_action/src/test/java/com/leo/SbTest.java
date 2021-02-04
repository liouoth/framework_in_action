package com.leo;

import com.leo.aspect.UserServiceImplPlus;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SbTest {
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext9.xml");
        UserServiceImplPlus u = (UserServiceImplPlus)ctx.getBean("com.leo.aspect.UserServiceImplPlus#0");
        //[com.leo.aspect.UserServiceImplPlus#0, com.leo.aspect.UserServiceImplPlus#1]
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
    }
}
