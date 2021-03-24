package com.example.springboot_redis.contoller;

import com.example.springboot_redis.redis.DistributedRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式Redis锁测试controller
 */
@RestController
@RequestMapping("/lock")
public class LockTestController {

    @Autowired
    private DistributedRedisLock distributedRedisLock;

    // 测试分布式锁
    @GetMapping("/testLock")
    public void testLock() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                distributedRedisLock.lock("LOCK");
            }).start();
        }
    }

}
