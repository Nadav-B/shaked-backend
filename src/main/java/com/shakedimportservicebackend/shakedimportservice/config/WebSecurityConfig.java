
package com.shakedimportservicebackend.shakedimportservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        switch (activeProfile) {
            case "local":
                http.
                        cors()
                        .and()
                        .csrf().disable();
                break;
            default:
                http
                        .authorizeRequests()
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
                        // admin
                        .antMatchers("/admin").permitAll()
                        .antMatchers("/admin/**").permitAll()

                        // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
                        .and().httpBasic()
                        .and().csrf()
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                break;
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("shai").password("{noop}shai").roles("ADMIN");
    }

}


