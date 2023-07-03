package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Members;
import com.example.wxy.entity.Relation;
import com.example.wxy.mapper.MembersMapper;
import com.example.wxy.mapper.RelationMapper;
import com.example.wxy.service.MembersService;
import com.example.wxy.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Relation")
public class RelationController {
    @Autowired
    private RelationService relationService;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private MembersMapper membersService;

    @GetMapping("/list")
    public List<Relation> list(){return relationService.list();}
    @PostMapping("/save")//增
    public boolean save(@RequestBody Relation relation){return relationService.save(relation);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Relation relation){return relationService.updateById(relation);}
    @RequestMapping("/delete")//删
    public boolean delete(@RequestBody Relation relation){
        return relationMapper.delete(relation.getMembersId1(),relation.getMembersId2());}
    @PostMapping("/list2")
    public List<Relation> list2(@RequestBody Relation relation){
        List<Relation> list = new ArrayList<>();
        if ("".equals(relation.getRelationType())|| relation.getRelationType() == null){
            list =  relationService.list();
        }else{
            LambdaQueryWrapper<Relation> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(Relation::getMembersId1,relation.getMembersId1());
            list = relationService.list(lambdaQueryWrapper);
        }
        for (int i = 0; i < list.size(); i++) {
            Members members1 = membersService.getByFamilyId(list.get(i).getMembersId1());
            if(members1!=null){
                list.get(i).setMembers1Name(members1.getMembersName());
            }
            Members members2 = membersService.getByFamilyId(list.get(i).getMembersId2());
            if(members2!=null){
                list.get(i).setMembers2Name(members2.getMembersName());
            }
        }
        return list;

    }

}
