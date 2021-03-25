package com.example.springboot_redis.service;

import com.example.springboot_redis.domain.User;

public interface UserService {
    User save(User user);

    User get(String userCode);

    void delete(String userCode);

    User update(User user);
}
