package com.example.wxy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Members {
    @TableId
    private int MembersId;
    private int FamilyId;
    private String MembersName;
    private String MembersBirthday;
    private String MembersDeath;


}
