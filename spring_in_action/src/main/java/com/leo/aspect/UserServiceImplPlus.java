package com.leo.aspect;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserServiceImplPlus implements UserService, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void login() {
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.check();
        System.out.println("====login===");
    }

    @Override
    public void check() {
        System.out.println("====check===");
    }
}
