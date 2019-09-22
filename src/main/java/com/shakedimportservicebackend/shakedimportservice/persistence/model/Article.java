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

    private Date modificationDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

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
