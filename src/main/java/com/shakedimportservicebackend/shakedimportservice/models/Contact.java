package com.shakedimportservicebackend.shakedimportservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import io.leangen.graphql.annotations.GraphQLId;
import io.leangen.graphql.annotations.GraphQLNonNull;
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
    @GraphQLId
    private Long id;

    @NotNull
    @GraphQLNonNull
    private String fullName;
    @NotNull
    @GraphQLNonNull
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
