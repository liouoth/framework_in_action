package com.leo.springboot_shiro.controller;

import com.leo.springboot_shiro.entity.User;
import com.leo.springboot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/login")
    public String login(@RequestParam String password, @RequestParam String username) {
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if (subject.isAuthenticated()) {
            return "index";
        } else {
            return "login";
        }
    }

    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam String password, @RequestParam String username) {
        User user = userService.register(username, password);
        if (user != null) {
            return "login";
        }
        return "register";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
