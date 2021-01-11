package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.controllers.ArticleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class ArticleControllerTest {


    @Autowired
    private ArticleController articleController;


    @Test
    void uploadFile() {

        try {
            File resource = new ClassPathResource("example.html").getFile();
            System.out.println(resource.getPath());
            MultipartFile multipartFile = new MockMultipartFile("textFile", new FileInputStream(resource));
            String content = new String(multipartFile.getBytes());
            System.out.println(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}