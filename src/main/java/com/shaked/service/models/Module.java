package com.shaked.service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
public class Module implements Timestamp {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String tag;
    @Column(columnDefinition = "LONGTEXT")
    private String introduction;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private Integer mediaId;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    private String contactButton;
    private ModuleType type;

    @Tolerate
    public Module() {
    }


}
