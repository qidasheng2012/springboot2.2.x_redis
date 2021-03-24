package com.example.springboot_redis.contoller;

import com.example.springboot_redis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Redis的controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    // 保存字符串
    @PostMapping("/{key}/{value}")
    public String setString(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "SUCCESS";
    }

    // 获取字符串
    @GetMapping("/{key}")
    public String getString(@PathVariable(name = "key") String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // 保存对象
    @PostMapping("/entity")
    public String postEntity(@RequestBody User user) {
        redisTemplate.opsForValue().set(user.getUserCode(), user);
        return "SUCCESS";
    }

    // 获取对象
    @GetMapping("/entity/{key}")
    public User getEntity(@PathVariable(name = "key") String key) {
        return (User) redisTemplate.opsForValue().get(key);
    }

}
