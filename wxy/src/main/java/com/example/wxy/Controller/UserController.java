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
import java.util.List;
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
        lambdaQueryWrapper.eq(User::getId,user.getId());
        return userService.list(lambdaQueryWrapper);
    }
}


