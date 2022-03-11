package com.shaked.service.controllers;

import com.shaked.service.models.ApplicationUser;
import com.shaked.service.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private String username;
    private String password;

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          @Value("${management.admin.username}") String username,
                          @Value("${management.admin.password}") String password
    ) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.username = username;
        this.password = password;
        setAdmin();
    }

    private void setAdmin() {
        applicationUserRepository.deleteAll();
        applicationUserRepository.save(ApplicationUser.builder().username(username).password(bCryptPasswordEncoder.encode(password)).build());
    }



    @GetMapping("/status")
    public HttpStatus sessionStatus() {
        return HttpStatus.OK;
    }
}