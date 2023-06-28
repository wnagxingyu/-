package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import sun.dc.pr.PRError;

@Data
public class Members {
    @TableId
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


}
