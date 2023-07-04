package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Things;
import com.example.wxy.mapper.ThingsMapper;
import com.example.wxy.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Things")
public class ThingsController {
    @Autowired
    private ThingsService thingsService;

    @Autowired
    private ThingsMapper thingsMapper;
    @GetMapping("/list")
    public List<Things> list(@RequestBody Things things){return thingsService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Things things){return thingsService.save(things);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Things things){return thingsService.updateById(things);}
    @RequestMapping("/delete")//删
    public boolean delete(@RequestBody Things things){
        return thingsMapper.delete(things.getMembersId());}
    @PostMapping("/list2")
    public List<Things> list2(@RequestBody Things things){
        if ("".equals(things.getThingsType())|| things.getThingsType() == null){
            return thingsService.list();
        }
        LambdaQueryWrapper<Things> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Things::getMembersId,things.getMembersId());
        return thingsService.list(lambdaQueryWrapper);
    }
}
