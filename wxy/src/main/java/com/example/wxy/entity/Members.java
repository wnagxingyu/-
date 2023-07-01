package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import sun.dc.pr.PRError;

import java.util.Date;

@Data
public class Members {
    @TableId(type = IdType.AUTO)
    private int MembersId;
    private int FamilyId;
    private String MembersSex;
    private String MembersName;
    @DateTimeFormat(pattern = "YYY-MM-DD")
    private String MembersBirthday;
    @DateTimeFormat(pattern = "YYY-MM-DD")
    private String MembersDeath;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private int Deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date updatetime;



}
