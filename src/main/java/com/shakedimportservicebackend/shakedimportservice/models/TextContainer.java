package com.shakedimportservicebackend.shakedimportservice.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
public class TextContainer {

    @Id
    @GeneratedValue
    private long id;

    private String tag;

    @Column(columnDefinition = "LONGTEXT")
    private String content;


    @Tolerate
    public TextContainer() {
    }

    public void modifyInformation(TextContainer modifedInformation) {
        this.setTag(modifedInformation.getTag());
        this.setContent(modifedInformation.getContent());
    }

}
