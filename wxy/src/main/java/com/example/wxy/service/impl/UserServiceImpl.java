package com.example.wxy.service.impl;

import com.example.wxy.mapper.UserMapper;
import com.example.wxy.entity.User;
import com.example.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public List<User> getAllUsers(){
        return userMapper.findAll();
    }
    @Override
    public User

     //public boolean add(User user) {
        //return userMapper.insertUser(user)>0;
    //}
}
