package com.leo;

import com.leo.entity.Animal;
import com.leo.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsInjectTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Animal animal = (Animal) ctx.getBean("animal");
        System.out.println(animal);
    }
}
