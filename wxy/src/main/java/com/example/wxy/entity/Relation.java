package com.example.wxy.entity;

import lombok.Data;

@Data
public class Relation {
    private int MembersId1;
    private int MembersId2;
    private String RelationType;
}
