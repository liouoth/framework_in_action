package com.leo;

import com.leo.entity.Person;
import com.leo.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注入
 */
public class injectionTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = (Person) ctx.getBean("person");
        //我们可以通过person 的 get/set方法进行属性赋值
        person.setName("123");
        //首先会有耦合，如果person不想叫123，那么就需要更改代码
        //另外person需要复用的话，直接就可以获取，获取person再setName了
        System.out.println(person);
    }

    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

    //注入自定义类型的对象
    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.getUserDao().print();
    }

    @Test
    public void test4(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = (Person) ctx.getBean("personSimplify");
        System.out.println(person);
    }
    @Test
    public void test5(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userServiceSimplify");
        userService.getUserDao().print();
    }
}
