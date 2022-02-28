package com.shaked.service.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;

    private String email;
    private String address;
    private String category;
    @NonNull
    @Builder.Default
    private Date date = new Date();
    private String comment;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Survey survey;


    @Tolerate
    public Contact() {
    }


}
