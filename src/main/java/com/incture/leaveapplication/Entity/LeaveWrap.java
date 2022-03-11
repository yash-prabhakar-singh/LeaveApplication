package com.incture.leaveapplication.Entity;




public class LeaveWrap {
    
    
private int level;
private char type;
private String description;
private String appliedby;




    public LeaveWrap() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public LeaveWrap(int level, char type, String description, String appliedby) {
        this.level = level;
        this.type = type;
        this.description = description;
        
        this.appliedby= appliedby;
    }

   
    public String getAppliedby() {
        return appliedby;
    }

    public void setAppliedby(String appliedby) {
        this.appliedby = appliedby;
    }
}
