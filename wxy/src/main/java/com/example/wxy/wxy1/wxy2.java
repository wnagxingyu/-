package com.example.wxy.wxy1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller


public class wxy2 {
    @RequestMapping(value = "index2")
    @ResponseBody
    public String index2(){
        return "nd";
    }
}
