package com.leo.springboot_shiro.controller;

import com.leo.springboot_shiro.entity.User;
import com.leo.springboot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/testRedis")
    @ResponseBody
    public String register() {
        redisTemplate.opsForValue().get("123");
        redisTemplate.opsForValue().set(String.valueOf(System.currentTimeMillis()), "123");
        return "register";
    }

}
