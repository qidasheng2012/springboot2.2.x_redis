package com.example.springboot_redis.contoller;

import com.example.springboot_redis.token.ActionToken;
import com.example.springboot_redis.token.TokenVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private ActionToken actionToken;

    @GetMapping("/createToken")
    public String createToke() {
        return actionToken.createToken("123456");
    }

    @TokenVerify
    @GetMapping("/test")
    public void testToken() {
        log.info("正常业务逻辑");
    }
}
