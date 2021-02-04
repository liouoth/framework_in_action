package com.leo.service;

import com.leo.dao.UserDao;
import com.leo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Transactional(rollbackFor = RuntimeException.class)
    public void register(User user){
        userDao.save(user);
        if (userDao.find(user)>0){
            System.out.println("进行回滚！");
            throw new RuntimeException();
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
