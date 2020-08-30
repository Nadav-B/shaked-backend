package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String fullname;
    @NotNull
    private String phonenumber;

    private String email;
    private String address;
    private String category;
    private Date date;
    private boolean markAsRead;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Survey survey;

    @Tolerate
    public Contact() {
    }


}
