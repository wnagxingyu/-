package com.example.wxy.Controller;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.service.UserService;
import com.example.wxy.mapper.UserMapper;
import com.example.wxy.entity.User;
import com.example.wxy.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping("/save")//增
    public boolean save(@RequestBody User user) {
        return userService.save(user);}

    @PutMapping("/mod")//改
    public boolean mod( @RequestBody User users) {
        return userService.updateById(users);
    }

    @DeleteMapping("/delete")//删
    public boolean delete(String  id) {
        return userService.removeById(id);
    }
    @PostMapping("/list2")
    public List<User> list2(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        return userService.list(lambdaQueryWrapper);
    }

    /**
     * @author
     * 登录接口
     */
    @RequestMapping("/login")
    public Map login(@RequestBody User users){
        Map map = new HashMap<>();
//        根据用户名查询
        User user1 = userService.getByName(users.getUsername());
        if(user1 != null){
//            判断密码是否相等
            if(user1.getPassword().equals(users.getPassword())){
                map.put("code",200);
                map.put("msg","登录成功");
                map.put("data",user1);
            }else{
                map.put("code",-1);
                map.put("msg","密码错误!");
            }
        }else{
            map.put("code",-1);
            map.put("msg","账号错误!");
        }
        return map;
    }


}


