package com.leo.shiro_thymeleaf.service;

import com.leo.shiro_thymeleaf.dao.UserDao;
import com.leo.shiro_thymeleaf.entity.User;
import com.leo.shiro_thymeleaf.util.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String salt = SaltUtil.getSalt(6);
        String md5SaltString = new Md5Hash(password, salt, 1024).toHex();
        user.setPassword(md5SaltString);
        user.setSalt(salt);
        return userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
