package com.example.wxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wxy.entity.Family;
import com.example.wxy.entity.Members;

import java.util.List;

public interface MembersMapper extends BaseMapper<Members> {

    Members getMax();

    boolean delete(int membersId);

    Members getByFamilyId(int membersId);
    List<Members> get(int familyId);

}
