package com.springsecurity.SecurityWithHibernate.model;

import javax.persistence.*;
@Entity
@Table(name="user_roles")
public class UserRole{
    private String roleId;
    private String userRole;
    private User user;
   

    @Id
    @Column(name="RoleId",nullable=false)
    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId=roleId;
    }
    
    @Column(name="UserRole",nullable=false)
    public String getRoleName(){
        return userRole;
    }

    public void setRoleName(String userRole)
    {
        this.userRole=userRole;
    }
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="Username")
   public User getUser(){
       return user;
   }
   public void setUser(User user){
       this.user=user;
   }
}