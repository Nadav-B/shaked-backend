package com.shaked.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue
    private long id;

    private String path;

    private String title;

    private String introduction;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private String contactButton;


}
