
package com.shakedimportservicebackend.shakedimportservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().
                authorizeRequests()
                .antMatchers("/articles").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers("/images").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers("images/**").authenticated() // Any resources not mentioned above needs to be authenticated
                .and().formLogin().loginPage("/login").failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("shai").password("shai").roles("ADMIN");
    }


}


