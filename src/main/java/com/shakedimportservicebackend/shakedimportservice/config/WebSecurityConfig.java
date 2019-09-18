
package com.shakedimportservicebackend.shakedimportservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //allowing unrestricted access to all endpoints.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()

                .csrf().disable()
                /*
                .authorizeRequests()
                .antMatchers("/api/articles").hasRole("ADMIN")

                 */
        ;
    }


}


