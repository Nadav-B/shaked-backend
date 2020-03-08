package com.shakedimportservicebackend.shakedimportservice.persistence.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Builder
@Entity
@Data
public class Answer {

    @Id
    private long id;

    private String question;
    private String answer;


    /*
    @ManyToOne
    @JoinColumn(name = "survey_id")
    @JsonBackReference
    @MapsId
    private Survey survey;
*/
    @Tolerate
    public Answer() {

    }
}
