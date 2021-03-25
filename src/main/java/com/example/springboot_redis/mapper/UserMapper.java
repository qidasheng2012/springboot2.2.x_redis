package com.example.springboot_redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_redis.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select id, user_code as userCode, name, age from user where user_code = #{userCode} limit 1")
    User selectByUserCode(String userCode);

    @Delete("delete from user where user_code = #{userCode} limit 1")
    void deleteByUserCode(String userCode);
}
