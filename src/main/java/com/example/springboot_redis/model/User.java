package com.example.springboot_redis.model;

import lombok.Data;

/**
 * @author qp
 * @date 2019/7/19 9:47
 */
@Data
public class User {
    // 主键
    private Integer id;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
}
