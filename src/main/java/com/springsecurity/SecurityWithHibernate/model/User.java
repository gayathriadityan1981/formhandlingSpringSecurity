package com.springsecurity.SecurityWithHibernate.model;

import javax.persistence.*;
@Entity
@Table(name="users")
public class User{
    private String username;
    private String password;
    private boolean enabled;

    @Id
    @Column(name="username",length=36,nullable=false)
    public String getUsername(){
        return username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }
    
    @Column(name="password",nullable=false)
    public String getPassword(){
        return password;
    }

    public void setPassword(String username)
    {
        this.username=username;
    }
    
    @Column(name="enabled",nullable=false)
    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled=enabled;
    }
}
