package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Family;
import com.example.wxy.entity.Members;
import com.example.wxy.service.FamilyService;
import com.example.wxy.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Members")
public class MembersController {
    @Autowired
    private MembersService membersService;
    @GetMapping("/list")
    public List<Members> list(){return membersService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Members members){return membersService.save(members);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Members members){return membersService.updateById(members);}
    @DeleteMapping("/delete")//删
    public boolean delete(String MembersId){return membersService.removeById(MembersId);}
    @PostMapping("/list2")
    public List<Members> list2(@RequestBody Members members){
        LambdaQueryWrapper<Members> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Members::getMembersId,members.getMembersId());
        return membersService.list(lambdaQueryWrapper);
    }
}
