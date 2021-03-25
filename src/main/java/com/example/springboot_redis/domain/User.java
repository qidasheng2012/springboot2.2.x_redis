package com.example.springboot_redis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {
    // 主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户编号
    private String userCode;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
}
