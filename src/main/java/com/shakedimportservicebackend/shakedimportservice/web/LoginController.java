package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.User;
import com.shakedimportservicebackend.shakedimportservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private User userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    @CrossOrigin("${website.cros}")
    public boolean login(@RequestBody User user) {
        return
                user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @PostMapping("/setDefault")
    @CrossOrigin("${website.cros}")
    public void setDefault() {
        User user = new User();
        user.setUserName("user");
        user.setPassword("wrongpassword");
        userRepository.save(user);
        System.out.println("saved");
    }
    }