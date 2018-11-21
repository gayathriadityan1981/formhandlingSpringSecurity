package com.springsecurity.SecurityWithHibernate.dao;
import java.util.List;
 
import com.springsecurity.SecurityWithHibernate.model.User;
import com.springsecurity.SecurityWithHibernate.model.UserRole;
import com.springsecurity.SecurityWithHibernate.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
@Repository
@Transactional
public class UserInfoDao{

    @Autowired
    private SessionFactory sessionFactory;
 
    public UserInfoDao() {
 
    }

    public UserInfo findUserInfo(String userName) {
        Object userInfo=null;
        String sql = "Select new " + UserInfo.class.getName() + "(u.username,u.password) "//
                + " from " + User.class.getName() + " u where u.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);


        userInfo= (Object) query.getSingleResult();
        return (UserInfo)userInfo;
    }
 
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.roleName "//
                + " from " + UserRole.class.getName() + " r where r.user.username = :username ";
 
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
         
        List<String> roles = query.getResultList();
       for(String row:roles )
        {
                roles.add(row.toString());
        }
        return roles;
    }
}
