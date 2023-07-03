package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Family;
import com.example.wxy.entity.User;
import com.example.wxy.mapper.FamilyMapper;
import com.example.wxy.service.FamilyService;
import com.example.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/Family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyMapper familyMapper;

    @GetMapping("/list")
    public List<Family>list(){return familyService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Family family){
        family.setFamilyId(familyMapper.getMax().getFamilyId()+1);
        return familyService.save(family);
    }
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Family family){return familyService.updateById(family);}
    @RequestMapping("/delete")//删
    public boolean delete(@RequestBody Family family){
        return familyMapper.delete(family.getFamilyId());}
    @PostMapping("/list2")
    public List<Family> list2(@RequestBody Family family){
        if ("".equals(family.getFamilyName())|| family.getFamilyName() == null){
            return familyService.list();
        }
        LambdaQueryWrapper<Family> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Family::getFamilyName,family.getFamilyName());
        return familyService.list(lambdaQueryWrapper);
    }

}
