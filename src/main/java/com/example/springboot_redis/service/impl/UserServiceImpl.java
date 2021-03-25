package com.example.springboot_redis.service.impl;

import com.example.springboot_redis.domain.User;
import com.example.springboot_redis.mapper.UserMapper;
import com.example.springboot_redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final String CACHE_KEY = "USER";

    @Autowired
    private UserMapper userMapper;

    @Override
    @CachePut(key = "#user.userCode", value = CACHE_KEY)
    public User save(User user) {
        log.info("保存用户");
        userMapper.insert(user);
        return user;
    }

    @Override
    @Cacheable(key = "#userCode", value = CACHE_KEY)
    public User get(String userCode) {
        log.info("获取用户userCode = {}", userCode);
        return userMapper.selectByUserCode(userCode);
    }

    @Override
    @CachePut(key = "#user.userCode", value = CACHE_KEY)
    public User update(User user) {
        log.info("更新用户userCode = {}", user.getUserCode());
        userMapper.updateById(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#userCode", value = CACHE_KEY)
    public void delete(String userCode) {
        log.info("删除用户userCode = {}", userCode);
        userMapper.deleteByUserCode(userCode);
    }


}
