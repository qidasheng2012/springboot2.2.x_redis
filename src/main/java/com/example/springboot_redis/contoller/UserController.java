package com.example.springboot_redis.contoller;

import com.example.springboot_redis.domain.User;
import com.example.springboot_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Redis的controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 新增用户
    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    // 查询用户
    @GetMapping("/{userCode}")
    public User get(@PathVariable(name = "userCode") String userCode) {
        return userService.get(userCode);
    }

    // 更新用户
    @PutMapping
    public void update(@RequestBody User user){
        userService.update(user);
    }

    // 删除用户
    @DeleteMapping("/{userCode}")
    public void delete(@PathVariable(name = "userCode") String userCode) {
        userService.delete(userCode);
    }

}
