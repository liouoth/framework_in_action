package com.leo.springboot_shiro.dao;

import com.leo.springboot_shiro.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
