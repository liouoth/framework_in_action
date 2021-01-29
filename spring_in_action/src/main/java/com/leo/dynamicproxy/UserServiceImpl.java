package com.leo.dynamicproxy;


import com.leo.annotation.Log;

public class UserServiceImpl implements UserService {
    @Override
    @Log
    public void register() {
        System.out.println("注册操作！");
    }

    @Override
    public void login() {
        System.out.println("登录操作！");
    }
}
