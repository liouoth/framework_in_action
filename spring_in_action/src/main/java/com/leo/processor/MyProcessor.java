package com.leo.processor;

import com.leo.entity.Product;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product){
            Product product = (Product) bean;
            product.setId(1829182);
            return product;
        }
        return bean;
    }
}
