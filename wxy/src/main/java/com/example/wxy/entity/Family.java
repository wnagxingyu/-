package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Family {
    private int FamilyId;
    private int id;
    private String FamilyName;
    private String FamilyContent;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String FamilyTime;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int Deleted;
    @TableField(fill =  FieldFill.INSERT)
    private Date updatetime;
}