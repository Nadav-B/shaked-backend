package com.shaked.service.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class Contact implements Timestamp {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private String email;
    private String address;
    private String category;
    @LastModifiedDate
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Survey survey;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;


    @Tolerate
    public Contact() {
    }


}
