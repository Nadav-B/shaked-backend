package com.shakedimportservicebackend.shakedimportservice.controllers;

import com.shakedimportservicebackend.shakedimportservice.models.ApplicationUser;
import com.shakedimportservicebackend.shakedimportservice.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${management.admin.username}")
    private String username;
    @Value("${management.admin.password}")
    private String password;


    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/record")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @GetMapping("/status")
    public HttpStatus sessionStatus() {
        return HttpStatus.OK;
    }
}