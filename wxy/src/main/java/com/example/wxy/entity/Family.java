package com.example.wxy.entity;

public class Family {
    private int FamilyId;
    private int id;
    private String FamilyName;
    private String FamilyContent;
    private String FamilyTime;

    public int getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(int familyId) {
        FamilyId = familyId;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getFamilyContent() {
        return FamilyContent;
    }

    public void setFamilyContent(String familyContent) {
        FamilyContent = familyContent;
    }

    public String getFamilyTime() {
        return FamilyTime;
    }

    public void setFamilyTime(String familyTime) {
        FamilyTime = familyTime;
    }

    public int getId() {
        return id;
    }

    public void setid(int id) {
       this.id  = id;
    }

    @Override
    public String toString() {
        return "Family{" +
                "FamilyId=" + FamilyId +
                ", id=" + id +
                ", FamilyName='" + FamilyName + '\'' +
                ", FamilyContent='" + FamilyContent + '\'' +
                ", FamilyTime='" + FamilyTime + '\'' +
                '}';
    }
}
