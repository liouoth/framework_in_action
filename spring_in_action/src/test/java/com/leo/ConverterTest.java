package com.leo;

import com.leo.entity.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConverterTest {
    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext1.xml");
        Product product = (Product) ctx.getBean("product");
        System.out.println(product);
    }
}
