package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.User;
import com.shakedimportservicebackend.shakedimportservice.repo.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@RestController
@Log
@RequestMapping("/login")
@CrossOrigin("${website.cros}")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    @CrossOrigin
    public boolean login(@RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUserName());
        if(optionalUser.isPresent()){
            return bCryptPasswordEncoder.matches(user.getPassword(), optionalUser.get().getPassword());
        }
        return false;
    }

    @PostMapping("/default")
    private void postDefaultUser(){
        User defaultUser  = User.builder().userName("shai").password(bCryptPasswordEncoder.encode("shai")).build();
        userRepository.save(defaultUser);
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }


}