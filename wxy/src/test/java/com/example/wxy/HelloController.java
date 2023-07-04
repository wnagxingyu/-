package com.example.wxy;
import com.example.wxy.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController


public class HelloController {
  /*  @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String name, int age) {
        String sage = String.valueOf(age);
        return "name=" + name + " " + "age=" + sage;
    }

    @RequestMapping(value = "index2", method = RequestMethod.POST)

    public String index2(@RequestBody User user) {
        System.out.println(user);
        return "" + user;
        //return "name="+name+",password="+password;
    }

    @RequestMapping(value = "test/*", method = RequestMethod.GET)
    public String test() {
        return "通配符匹配";
    }*/
}
