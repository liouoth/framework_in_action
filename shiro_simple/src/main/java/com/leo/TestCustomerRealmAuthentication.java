package com.leo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

//自定义的customer realm
public class TestCustomerRealmAuthentication {
    //创建security manager
    public static void main(String[] args) {
        //new security管理
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //设置realm
        securityManager.setRealm(new CustomRealm());

        //一般基于全局的安全工具类进行操作
        SecurityUtils.setSecurityManager(securityManager);

        //登录主体
        Subject subject = SecurityUtils.getSubject();

        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaomao","1234");

        //主体设置信息，然后执行主体的方法，登录
        //IncorrectCredentialsException 信息凭证错误
        //UnknownAccountException 未知用户名
        try {
            subject.login(token);
            System.out.println("是否登录成功："+subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
