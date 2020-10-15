package com.leo.springboot_shiro.service;

import com.leo.springboot_shiro.dao.UserDao;
import com.leo.springboot_shiro.entity.User;
import com.leo.springboot_shiro.util.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User register(String username,String password){
        User user = new User();
        user.setUsername(username);
        String salt = SaltUtil.getSalt(6);
        String md5SaltString = new Md5Hash(password,salt,1024).toHex();
        user.setPassword(md5SaltString);
        user.setSalt(salt);
        return userDao.save(user);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
