package com.leo.springboot_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        //获取对象
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            return "可以创建";
        } else {
            return "不可以创建";
        }

    }

    @RequestMapping("/update")
    @ResponseBody
    //可以使用logical进行操作关系
    @RequiresRoles(value = {"user", "admin"}, logical = Logical.OR)
//    @RequiresPermissions("user:update:people")
    public String update() {
        return "可以使用";
    }
}
