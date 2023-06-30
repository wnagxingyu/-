package com.example.wxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wxy.mapper.UserMapper;
import com.example.wxy.entity.User;
import com.example.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}


