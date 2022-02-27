package com.incture.leaveapplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table
public class Leave {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
private int level;
private char type;
private String description;
private String appliedby;

@ManyToOne(fetch=FetchType.LAZY, optional = false)
@JoinColumn(name="eid",nullable = false)
@OnDelete(action= OnDeleteAction.CASCADE)
//@JsonIgnore
private Employee employee;

    public Leave() {

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

    @JsonIgnore
   public Employee getEmployee() {
        return employee;
    }

    public Leave(int level, char type, String description, Employee E, String appliedby) {
        this.level = level;
        this.type = type;
        this.description = description;
        this.employee= E;
        this.appliedby= appliedby;
    }

    public Integer getId() {
        return id;
    }

    public String getAppliedby() {
        return appliedby;
    }

    public void setAppliedby(String appliedby) {
        this.appliedby = appliedby;
    }
}
