package com.leo;


import com.leo.entity.Person;
import com.leo.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class AppTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

    @Test
    public void applicationContext_func(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        //根据id获取Bean
        Person person1 = (Person) ctx.getBean("person");
        //根据id和Bean类型获取Bean，避免了强转
        Person person2 = ctx.getBean("person",Person.class);
        //根据Bean类型获取Bean，如果bean类型存在重复，则会报错
        Person person3 = ctx.getBean(Person.class);
        //获取所有bean.xml中定义的所有bean的id
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        //获取所有bean.xml中所有指定类型bean的id
        String[] specifyClassBeanDefinitionNames = ctx.getBeanNamesForType(Person.class);
        //判断bean.xml中是否定义了id为参数的bean
        System.out.println(ctx.containsBeanDefinition("person"));
        System.out.println(ctx.containsBean("person"));
    }

    //bean定义中不加id
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        User user = ctx.getBean(User.class);
        System.out.println(user);
        //查询这个bean是否存在id
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
    }
    //通过name值获取bean
    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        User user = (User) ctx.getBean("u"); //可以根据name获取到bean

        //name值为u
        System.out.println(ctx.containsBeanDefinition("u"));
        System.out.println(ctx.containsBean("u"));
        System.out.println(user);
    }

}
