package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
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

    private String fullname;
    private String email;
    private String phonenumber;
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
