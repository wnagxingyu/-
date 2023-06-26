package com.example.wxy.mapper;

import com.example.wxy.entity.Family;
import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface FamilyMapper {
   @Select("select * from Family")
    public List<Family> find();
   @Insert("insert into Family values")
    public int insert(Family family);
}
