package com.leo.proxy;

//中介
public class UserServiceProxy implements UserService{
    private UserService userService = new UserServiceImpl();

    @Override
    public void register() {
        System.out.println("代理类 增强！！！");
        userService.register();
    }

    @Override
    public void login() {
        System.out.println("代理类 增强！！！");
        userService.login();
    }
}
