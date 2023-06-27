package com.example.wxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wxy.entity.Members;
import com.example.wxy.mapper.MembersMapper;
import com.example.wxy.service.MembersService;
import org.springframework.stereotype.Service;

@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements MembersService{
}