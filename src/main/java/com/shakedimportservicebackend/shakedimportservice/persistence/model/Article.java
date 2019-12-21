package com.shakedimportservicebackend.shakedimportservice.persistence.model;

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
    private long id;

    private String title;

    private String tag;

    @Column(columnDefinition = "LONGTEXT")
    private String introduction;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Lob
    private byte[] image;

    private Date modificationDate;


    @Tolerate
    public Article() {
    }

    public void modifyArticle(Article modifiedArticle) {
        this.setContent(modifiedArticle.getContent());
        this.setTitle(modifiedArticle.getTitle());
        this.setTag(modifiedArticle.getTag());
        this.setIntroduction(modifiedArticle.getIntroduction());
        this.setModificationDate(new Date());
    }

}
