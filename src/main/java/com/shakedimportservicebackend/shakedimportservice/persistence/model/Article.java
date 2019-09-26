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

    private String tag;

    @Column(columnDefinition = "LONGTEXT")
    private String introduction;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private long imageId;

    private Date modificationDate;


    @Tolerate
    public Article() {
    }

    public void modifyArticle(Article modifedArticle) {
        this.setContent(modifedArticle.getContent());
        this.setTitle(modifedArticle.getTitle());
        this.setTag(modifedArticle.getTag());
        this.setIntroduction(modifedArticle.getIntroduction());

    }


}
