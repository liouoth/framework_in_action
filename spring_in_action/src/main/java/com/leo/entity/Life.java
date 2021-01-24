package com.leo.entity;

import org.springframework.beans.factory.InitializingBean;

public class Life implements InitializingBean {
    private String name;

    public Life() {
        System.out.println("Life创建了！！");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
        System.out.println("初始化！！！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
