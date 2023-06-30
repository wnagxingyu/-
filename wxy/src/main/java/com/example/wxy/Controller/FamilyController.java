package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Family;
import com.example.wxy.entity.User;
import com.example.wxy.service.FamilyService;
import com.example.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;
    @GetMapping("/list")
    public List<Family>list(){return familyService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Family family){return familyService.save(family);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Family family){return familyService.updateById(family);}
    @DeleteMapping("/delete")//删
    public boolean delete(String FamilyId){return familyService.removeById(FamilyId);}
    @PostMapping("/list2")
    public List<Family> list2(@RequestBody Family family){
        LambdaQueryWrapper<Family> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Family::getFamilyName,family.getFamilyName());
        return familyService.list(lambdaQueryWrapper);
    }

}
