package com.shakedimportservicebackend.shakedimportservice.persistence.model;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Component
@Entity
public class User {

@Id
@GeneratedValue
private long id;

    private String userName;

    private String password;

    @Tolerate
    public User() {
    }


}