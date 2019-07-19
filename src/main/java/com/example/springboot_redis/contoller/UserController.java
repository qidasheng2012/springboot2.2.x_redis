package com.example.springboot_redis.contoller;

import com.example.springboot_redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author qp
 * @date 2019/7/19 9:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    // 获取字符串
    @GetMapping("/get/{key}")
    public String getRedis(@PathVariable(name = "key") String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // 保存字符串
    @PostMapping("/set/{key}/{value}")
    public String getRedis(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "SUCCESS";
    }

    // 保存对象
    @PostMapping("/postEntity")
    public String postEntity(@RequestBody User user) {
        redisTemplate.opsForValue().set(user.getId(), user);
        return "SUCCESS";
    }

    // 获取对象
    @GetMapping("/getEntity/{key}")
    public Object getEntity(@PathVariable(name = "key") Integer key) {
        return redisTemplate.opsForValue().get(key);
    }

}
