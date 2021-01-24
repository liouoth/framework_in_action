package com.leo.entity;

import org.springframework.beans.factory.InitializingBean;

public class Life2{
    private String name;

    public Life2() {
        System.out.println("Life创建了！！");
    }

    public void init() {
        System.out.println(name);
        System.out.println("初始化！！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
