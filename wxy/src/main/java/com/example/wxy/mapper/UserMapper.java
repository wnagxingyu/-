package com.example.wxy.mapper;


import com.example.wxy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //@Select("select * from User")//后面不加分号
   // public List<User> find();

    //@Insert("insert into User values(#{id},#{username},#{password})")
    //public  int insert(User user);
    //@Delete("delete from User where id=#{id}")
     //public int delete(int id);
   // @Update("update User set username=#{username},password=#{password} where id=#{id}")
    //public int update(User user);
    //@Select("select * from User where id=#{id}")
    //User selectById(int id);
}
