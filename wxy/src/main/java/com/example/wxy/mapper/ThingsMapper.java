package com.example.wxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wxy.entity.Things;

public interface ThingsMapper extends BaseMapper<Things> {

    boolean delete(int membersId);

}
