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

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    public Image(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }
}

