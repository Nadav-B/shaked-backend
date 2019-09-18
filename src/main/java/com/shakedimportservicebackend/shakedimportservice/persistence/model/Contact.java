package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Builder
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    private String fullname;
    private String email;
    private String phonenumber;
    private String address;
    private Date date;
private boolean markAsRead;

    @Tolerate
    public  Contact () {
    }


}
