package com.example.wxy.mapper;


import com.example.wxy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    User getByName(String username);

}
