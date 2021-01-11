package com.shakedimportservicebackend.shakedimportservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String tag;

    @Column(columnDefinition = "LONGTEXT")
    private String introduction;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Lob
    @JsonIgnore
    private byte[] image;

    private Date modificationDate;

    private String contactButton;



    @Tolerate
    public Article() {
    }

    public void modifyArticle(Article modifiedArticle) {
        this.setContent(modifiedArticle.getContent());
        this.setTitle(modifiedArticle.getTitle());
        this.setTag(modifiedArticle.getTag());
        this.setIntroduction(modifiedArticle.getIntroduction());
        this.setContactButton(modifiedArticle.getContactButton());
        this.setModificationDate(new Date());
    }

}
