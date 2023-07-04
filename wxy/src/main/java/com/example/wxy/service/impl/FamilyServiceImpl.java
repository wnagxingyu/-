package com.example.wxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wxy.entity.Family;
import com.example.wxy.mapper.FamilyMapper;
import com.example.wxy.service.FamilyService;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {
}
