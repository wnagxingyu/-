package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Things {
    private int MembersId;
    @DateTimeFormat(pattern = "YYY-MM-DD")
    private String ThingsTime;
    private String ThingsType;
    private String ThingsContent;
    private String ThingsAccessory;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int Deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date updatetime;
}
