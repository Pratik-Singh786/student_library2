package com.example.liberary.studentliberary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserService userService;

  //using userService for the authentication
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.
                csrf().
                disable(). //to disable 403 error which is generated due to csrf token
                httpBasic()
                .and().  //httpBasic will  guide it to authorization part and then return 401 i.e you are not a valid entity  after providing the username and password  in basic auth you will able to f etch the records
                authorizeRequests()
                .antMatchers(HttpMethod.PUT ,"/student/update_password**").hasAuthority(AuthorityConstants.STUDENT_AUTHORITY)//only student can update his password;
                .antMatchers(HttpMethod.PUT ,"/student/**").hasAnyAuthority(AuthorityConstants.STUDENT_AUTHORITY,AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET, "/student/all/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)//it means fetching all the details of the student you need admin level authority
                .antMatchers(HttpMethod.GET, "/student/studentById/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"/student/**").hasAnyAuthority(AuthorityConstants.STUDENT_AUTHORITY) //it means fetching single student  details  you need admin level authority
                .antMatchers("/student/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)//for post and delete apis

                .antMatchers(HttpMethod.GET,"/book/**").hasAnyAuthority(AuthorityConstants.STUDENT_AUTHORITY,AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers("/book/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY) //for any other api rather than get put,post delete only admin

                .antMatchers(HttpMethod.GET,"/transaction/all/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.GET,"transaction/**").hasAnyAuthority(AuthorityConstants.STUDENT_AUTHORITY,AuthorityConstants.ADMIN_AUTHORITY)
                .antMatchers("/transaction/**").hasAuthority(AuthorityConstants.ADMIN_AUTHORITY)

                .antMatchers("/**").permitAll()
                .and().formLogin();

    }
    @Bean
    public PasswordEncoder getPE()

    {
       return new BCryptPasswordEncoder() ;
    }




}

