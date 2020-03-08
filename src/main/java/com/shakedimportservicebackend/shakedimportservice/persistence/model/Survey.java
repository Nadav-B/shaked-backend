package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;


@Entity
@Builder
@Data

public class Survey   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(mappedBy = "survey")
    @JsonBackReference
    private Contact contact;


    /*
    @ElementCollection(targetClass = Answer.class)
    @OneToMany(fetch = FetchType.EAGER,mappedBy="survey",cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Answer> answers;

     */

    @Tolerate
    public Survey() {
    }
}
