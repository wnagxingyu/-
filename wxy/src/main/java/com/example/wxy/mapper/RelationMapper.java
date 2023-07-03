package com.example.wxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wxy.entity.Relation;

public interface RelationMapper extends BaseMapper<Relation> {

    boolean delete(int MembersId1,int MembersId2);

}
