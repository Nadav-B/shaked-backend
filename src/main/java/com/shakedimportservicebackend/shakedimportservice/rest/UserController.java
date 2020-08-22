package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Value("${management.admin.username}")
    private String username;
    @Value("${management.admin.password}")
    private String password;


    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}