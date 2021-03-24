package com.example.springboot_redis.token;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 处理防重复提交token切面
 */
@Slf4j
@Aspect
@Component
public class TokenAspect {

    @Autowired
    private ActionToken actionToken;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    // 切入点签名
    @Pointcut("@annotation(com.example.springboot_redis.token.TokenVerify)")
    private void tokenPoint() {
    }

    // 环绕通知
    @Around(value = "tokenPoint()")
    public Object tokenVerify(ProceedingJoinPoint joinPoint) throws Throwable {

        String msg = actionToken.tokenVerify(request.getParameter("token"));

        if (!StringUtils.hasText(msg)) {
            // 删除token成功，进入业务逻辑
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                log.error("删除token成功，业务处理异常:", e);
            }
        } else {
            try (PrintWriter printWriter = response.getWriter()) {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                printWriter.write(msg);
                printWriter.flush();
            } catch (Exception e) {
                log.error("处理token，返回错误信息时异常", e);
            }
        }

        return null;
    }

}
