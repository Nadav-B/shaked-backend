package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String comment;
    private boolean markAsRead;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Survey survey;

    @Tolerate
    public Contact() {
    }

    public void modifyContact(Contact modifedContact) {
        this.setComment(modifedContact.getComment());
        this.setMarkAsRead(modifedContact.markAsRead);
    }


}
