package com.shakedimportservicebackend.shakedimportservice.persistence.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    @OneToOne(mappedBy = "image")
    private Article article;

    public Image(String name, byte[] pic) {
        this.name = name;
        this.pic = pic;
    }
}

