
package com.shakedimportservicebackend.shakedimportservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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

                http.cors().and()
                        .csrf().disable()
                        .authorizeRequests()

                        // texts
                        .antMatchers(HttpMethod.GET,"/texts").permitAll()
                        .antMatchers( "/texts/text/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/texts/**").hasRole("ADMIN")
                        //services
                        .antMatchers(HttpMethod.GET, "/services").permitAll()
                        .antMatchers(HttpMethod.GET,"/services/**").hasRole("ADMIN")

                        //articles
                        .antMatchers(HttpMethod.GET,"/articles").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                        .antMatchers(HttpMethod.GET, "/articles/article/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/articles/**").hasRole("ADMIN") // Enabling URL to be accessed by all users (even un-authenticated)

                        //contacts
                        .antMatchers(HttpMethod.POST,"/contacts/insert").permitAll()
                        .antMatchers("/contacts").hasRole("ADMIN")

                        // admin
                        .antMatchers("/admin/login").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")

                      .anyRequest().authenticated()
                        .and().httpBasic();

                    //    .and()
                    //    .csrf()
                    //    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser(username).password(password).roles("ADMIN");

    }
}


