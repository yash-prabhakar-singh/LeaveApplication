package com.incture.leaveapplication.Entity;




public class LeaveWrap {
    
    

private char type;
private String description;





    public LeaveWrap() {

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

   

    public LeaveWrap(char type, String description) {
        
        this.type = type;
        this.description = description;
       
    }

   
  
}
