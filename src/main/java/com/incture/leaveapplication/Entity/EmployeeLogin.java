package com.incture.leaveapplication.Entity;


import java.util.ArrayList;
import java.util.List;

public class EmployeeWrap {



    private String password;

    private String email;




    public EmployeeWrap(String email, String password) {
        
        this.password = password;

        this.email = email;


    }

    public EmployeeWrap() {

    }





   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
