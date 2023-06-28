package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Things;
import com.example.wxy.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Things")
public class ThingsController {
    @Autowired
    private ThingsService thingsService;
    @GetMapping("/list")
    public List<Things> list(@RequestBody Things things){return thingsService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Things things){return thingsService.save(things);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Things things){return thingsService.updateById(things);}
    @DeleteMapping("/delete")//删
    public boolean delete(String MembersId){return thingsService.removeById(MembersId);}
    @PostMapping("/list2")
    public List<Things> list2(@RequestBody Things things){
        LambdaQueryWrapper<Things> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Things::getMembersId,things.getMembersId());
        return thingsService.list(lambdaQueryWrapper);
    }
}
