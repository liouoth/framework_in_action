package com.leo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class LifeTest {

    //测试，初始化是在属性注入之前还是属性注入之后
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("./applicationContext.xml");
    }
}
