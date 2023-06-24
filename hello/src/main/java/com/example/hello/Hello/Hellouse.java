package com.example.hello.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hellouse {
    @RequestMapping(value = "index")
    @ResponseBody
    public String index(){
        return "您已成功显示";
    }
}

