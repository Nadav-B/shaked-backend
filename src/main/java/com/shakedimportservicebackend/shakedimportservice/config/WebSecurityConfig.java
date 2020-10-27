
package com.shakedimportservicebackend.shakedimportservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@Profile("!local")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${management.admin.username}")
    private String username;
    @Value("${management.admin.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()

                // texts
                .antMatchers(HttpMethod.GET, "/texts").permitAll()
                .antMatchers("/texts/text/**").permitAll()
                //services
                .antMatchers(HttpMethod.GET, "/services").permitAll()
                .antMatchers(HttpMethod.GET, "/services/service/**").permitAll()


                .antMatchers("/graphql").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/graphiql").permitAll()

                //offers
                .antMatchers(HttpMethod.GET, "/offers/offer/**").permitAll()

                //articles
                .antMatchers(HttpMethod.GET, "/articles").permitAll() // Enabling URL to be accessed by all users (even un-authenticated)
                .antMatchers(HttpMethod.GET, "/articles/article/**").permitAll()

                //contacts
                .antMatchers(HttpMethod.POST, "/contacts/post").permitAll()

                // admin
                .antMatchers("/admin/login").permitAll()

                .anyRequest()
                .authenticated().and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(username)
                .password("{noop}" + password)
                .roles("USER");
    }
}
