package com.leo.shiro_thymeleaf.dao;

import com.leo.shiro_thymeleaf.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
