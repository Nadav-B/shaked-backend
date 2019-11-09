package com.shakedimportservicebackend.shakedimportservice.loader;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageLoader {

    public ImageLoader() {
    }

    public Image getImageFromRow(MultipartFile imageFile) throws IOException {
        if (imageFile != null) {
            Image image = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes());
            log.info("Image converted");
            return image;
        } else throw new IllegalArgumentException("Can't convert The image");

    }

}
