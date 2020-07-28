package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Data
@ToString(exclude = "contact")
@Builder
public class Survey   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(mappedBy = "survey")
    @JsonBackReference
    private Contact contact;

    @OneToMany(mappedBy="survey",cascade = CascadeType.ALL)
    @JsonManagedReference
    @Builder.Default
    private List<Answer> answers = new ArrayList<>() ;

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
