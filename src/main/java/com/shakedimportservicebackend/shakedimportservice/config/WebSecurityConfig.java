
package com.shakedimportservicebackend.shakedimportservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${management.admin.username}")
    private String username;
    @Value("${management.admin.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                        .authorizeRequests()

                        .antMatchers("/texts").permitAll()
                        .antMatchers( "/texts/text/**").permitAll()

                        //services
                        .antMatchers( "/services").permitAll()
                        //articles
                        .antMatchers("/articles").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                        .antMatchers( "/articles/article/**").permitAll()

                        //contacts
                        .antMatchers("/contacts/insert").permitAll()
                        // admin
                        .antMatchers("/admin/login").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN");
                     //   .anyRequest().authenticated();

                    //    .and()
                    //    .csrf()
                    //    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(username).password(password).roles("ADMIN");
    }

}


