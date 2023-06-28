package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Family {
    @TableId
    private int FamilyId;
    private int id;
    private String FamilyName;
    private String FamilyContent;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String FamilyTime;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int Deleted;
}