package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Family {
    @TableId
    private int FamilyId;
    private int id;
    private String FamilyName;
    private String FamilyContent;
    private String FamilyTime;

}