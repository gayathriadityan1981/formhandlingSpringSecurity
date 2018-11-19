package com.springsecurity.SecurityWithHibernate.model;

import javax.persistence.*;
@Entity
@Table(name="user")
public class User{
    private String username;
    private String password;
    private boolean enabled;

    @Id
    @Column(name="Username",length=36,nullable=false)
    public String getUsername(){
        return username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }
    
    @Column(name="Password",nullable=false)
    public String getPassword(){
        return password;
    }

    public void setPassword(String username)
    {
        this.username=username;
    }
    
    @Column(name="Enabled",nullable=false)
    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled=enabled;
    }
}