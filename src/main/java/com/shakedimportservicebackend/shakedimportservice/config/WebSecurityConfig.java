
package com.shakedimportservicebackend.shakedimportservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                // informations
                .antMatchers(HttpMethod.GET, "/texts").permitAll()
                //services
                .antMatchers(HttpMethod.GET, "/services").permitAll()
                //articles
                .antMatchers(HttpMethod.GET, "/articles").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers(HttpMethod.GET, "/articles/article/**").permitAll()
                //images
                .antMatchers(HttpMethod.GET, "/images").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers(HttpMethod.GET, "/images/image/**").permitAll()
                //contacts
                .antMatchers(HttpMethod.POST, "/contacts/insert").permitAll()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
                .and().httpBasic();


        //.formLogin().loginPage("/login").failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("shai").password("{noop}shai").roles("ADMIN");
    }


}


