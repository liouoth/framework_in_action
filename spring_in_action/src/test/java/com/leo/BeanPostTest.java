package com.leo;

import com.leo.entity.Product;
import com.leo.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext2.xml");
        Product product =(Product) ctx.getBean("product");
        User user =(User) ctx.getBean("user");
        System.out.println(product.getId());
    }
}
