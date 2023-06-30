package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Members;
import com.example.wxy.entity.Relation;
import com.example.wxy.service.MembersService;
import com.example.wxy.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Relation")
public class RelationController {
    @Autowired
    private RelationService relationService;
    @GetMapping("/list")
    public List<Relation> list(){return relationService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Relation relation){return relationService.save(relation);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Relation relation){return relationService.updateById(relation);}
    @DeleteMapping("/delete")//删
    public boolean delete(String MembersId1){return relationService.removeById(MembersId1);}
    @PostMapping("/list2")
    public List<Relation> list2(@RequestBody Relation relation){
        LambdaQueryWrapper<Relation> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Relation::getMembersId1,relation.getMembersId1());
        return relationService.list(lambdaQueryWrapper);
    }
}
