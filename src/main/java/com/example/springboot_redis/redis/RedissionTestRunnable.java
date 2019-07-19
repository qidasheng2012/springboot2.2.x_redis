package com.example.springboot_redis.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author qp
 * @date 2019/7/19 16:03
 */
@Slf4j
@Component
public class RedissionTestRunnable implements Runnable {

    @Autowired
    private DistributedRedisLock distributedRedisLock;

    private final String LOCK_NAME = "DistributedRedisLockTestName";

    @Override
    public void run() {
        log.info("RedissionTestRunnable start");
        try {
            distributedRedisLock.lock(LOCK_NAME);
            log.info("正在执行任务");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("sleep 5 second Exception：", e);
        } finally {
            distributedRedisLock.unlock(LOCK_NAME);
        }
        log.info("RedissionTestRunnable end");
    }
}
