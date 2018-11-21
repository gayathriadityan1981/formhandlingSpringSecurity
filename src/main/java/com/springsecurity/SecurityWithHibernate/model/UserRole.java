package com.springsecurity.SecurityWithHibernate.model;

import javax.persistence.*;
@Entity
@Table(name="userroles")
public class UserRole{
    private String roleId;
    private String roleName;
    private User user;
   

    @Id
    @Column(name="roleId",nullable=false)
    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId=roleId;
    }
    
    @Column(name="roleName",nullable=false)
    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName=roleName;
    }
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="username")
   public User getUser(){
       return user;
   }
   public void setUser(User user){
       this.user=user;
   }
}
