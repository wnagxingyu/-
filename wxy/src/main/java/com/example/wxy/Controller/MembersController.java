package com.example.wxy.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wxy.entity.Family;
import com.example.wxy.entity.Members;
import com.example.wxy.mapper.FamilyMapper;
import com.example.wxy.mapper.MembersMapper;
import com.example.wxy.mapper.RelationMapper;
import com.example.wxy.service.FamilyService;
import com.example.wxy.service.MembersService;
import com.example.wxy.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Members")
public class MembersController {
    @Autowired
    private MembersService membersService;

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private RelationService relationService;

    @GetMapping("/list")
    public List<Members> list(){return membersService.list();}


    @PostMapping("/save")//增
    public boolean save(@RequestBody Members members){
        members.setMembersId(membersMapper.getMax().getMembersId()+1);
        return membersService.save(members);}
    @PutMapping("/mod")//改
    public boolean mod(@RequestBody Members members){return membersService.updateById(members);}
    @RequestMapping("/delete")//删
    public boolean delete(@RequestBody Members members){return membersMapper.delete(members.getMembersId());}
    @PostMapping("/list2")
    public List<Members> list2(@RequestBody Members members){
        if ("".equals(members.getMembersName())|| members.getMembersName() == null){
            return membersService.list();
        }
        LambdaQueryWrapper<Members> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Members::getMembersName,members.getMembersName());
        return membersService.list(lambdaQueryWrapper);
    }

    @PostMapping("/chat")
    public List<Members> chat(@RequestBody Members members){
        List<Members> list = membersMapper.get(members.getFamilyId());
        for (int i = 0; i < list.size(); i++) {
            Family family = familyMapper.get(list.get(i).getFamilyId());
            if(family!=null){
                list.get(i).setFamilyName(family.getFamilyName());
            }
        }
        return list;
    }
}
