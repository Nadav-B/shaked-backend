package com.shakedimportservicebackend.shakedimportservice.persistence.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
public class Article {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @Column(columnDefinition="LONGTEXT")
    private String content;

    private Date modificationDate;

    @Tolerate
    public Article(){}



}
