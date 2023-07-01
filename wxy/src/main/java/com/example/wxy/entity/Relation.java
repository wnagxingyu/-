package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class Relation {
    private int MembersId1;
    private int MembersId2;
    private String RelationType;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int Deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date updatetime;

}
