package com.incture.leaveapplication.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ID;
    @Column
    private String name, password;
    @Column(unique=true)
    private String email;
    @Column
    @OneToMany(mappedBy= "employee", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Leave> leavelist;


    public Employee(String name, String password, String email) {
        this.name = name;
        this.password = password;

        this.email = email;
        this.leavelist= new ArrayList<Leave>();

    }

    public Employee() {

    }

    public List<Leave> getLeaveList() {
        return leavelist;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leavelist = leaveList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
