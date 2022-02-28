package com.shaked.service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Data
@ToString(exclude = "survey")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name="survey_id",nullable = false)
    @JsonBackReference
    private Survey survey;


    @Tolerate
    public Answer() {

    }
}
