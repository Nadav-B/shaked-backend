package com.shaked.service.controllers;

import com.shaked.service.excepctions.NotFoundException;
import com.shaked.service.models.Article;
import com.shaked.service.models.Media;
import com.shaked.service.repositories.ArticleRepository;
import com.shaked.service.repositories.MediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/media")
@Slf4j
public class MediaController {
    private final MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @GetMapping("{id}")
    public @ResponseBody
    ResponseEntity getMedia(@PathVariable long id) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(media.getContent().length)
                .body(media.getContent());
    }
}



