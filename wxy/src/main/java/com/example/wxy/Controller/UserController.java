package com.example.wxy.Controller;
import org.springframework.http.HttpStatus;
import com.example.wxy.service.UserService;
import com.example.wxy.mapper.UserMapper;
import com.example.wxy.entity.User;
import com.example.wxy.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
      return ResponseEntity.ok().build();
    }
}


    //@RequestMapping("/add")
    //public void addUser(User user){
        //boolean add = userService.add(user);
        //System.out.println(add?"添加成功":"添加失败");
    //}

    //@ApiOperation("查询用户")
    //@GetMapping("/User")
    //public List query() {
       // List<User>list = userMapper.find();
       // System.out.println(list);
       // return list;
   // }

   //@ApiOperation("添加用户")
    //@PostMapping("/User")
    //public List save(User user) {
        //int i = userMapper.insert(user);
        //if (i > 0) {
           // return Collections.singletonList("插入成功");
       // }else {
           // return Collections.singletonList("插入失败");
       // }
   // }

   // @ApiOperation("更新用户")
   // @PutMapping("/User")
    //public List update(User user) {
       // userMapper.update(user);
       // return Collections.singletonList("修改成功");
    //}

       /// @ApiOperation("删除用户")
       // @DeleteMapping("/User")
       // public List delete (int id){
           // int i = userMapper.delete(id);
            //if (i > 0) {
               // return Collections.singletonList("删除成功");
           // } else {
               // return Collections.singletonList("删除失败");
            //}
    //}

    ///@GetMapping("/User/{id}")
     //public String getById(@PathVariable int id){
        //userMapper.findById(id);
        //System.out.println(id);
        //return "根据ID查询";
   // }
