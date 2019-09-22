package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Image;
import com.shakedimportservicebackend.shakedimportservice.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/images")
@CrossOrigin
@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    @CrossOrigin
    public Image uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        Image img = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());

        final Image savedImage = imageRepository.save(img);

        System.out.println("Image saved");


        return savedImage;


    }
}
