package com.springsecurity.SecurityWithHibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.springsecurity.SecurityWithHibernate.service.MyUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();

        // /userInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
         // Config for Login Form
         http.authorizeRequests().and().formLogin()//
         // Submit URL of login page.
         .loginProcessingUrl("/j_spring_security_check") // Submit URL
         .loginPage("/login")//
         .defaultSuccessUrl("/userInfo")//
         .failureUrl("/login?error=true")//
         .usernameParameter("username")//
         .passwordParameter("password")
         // Config for Logout Page
         .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

 
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {

         // Users in memory.
        authenticationMgr.inMemoryAuthentication()
        .withUser("user").password("12345")
            .authorities("ROLE_USER").and()
            .withUser("admin").password("12345")
           .authorities("ROLE_ADMIN");

        // For User in database.
        authenticationMgr.userDetailsService(myUserDetailsService);
 
           
    }

}
