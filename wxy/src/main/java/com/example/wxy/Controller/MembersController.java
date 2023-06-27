package com.example.wxy.Controller;

import com.example.wxy.entity.Family;
import com.example.wxy.entity.Members;
import com.example.wxy.service.FamilyService;
import com.example.wxy.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Members")
public class MembersController {
    @Autowired
    private MembersService membersService;
    @GetMapping("/list")
    public List<Members> list(){
        return membersService.list();
    }
}
