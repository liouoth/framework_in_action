package com.leo.dao;

import com.leo.entity.User;

public interface UserDao {
    public int save(User user);
    public int find(User user);
}
