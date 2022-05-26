package com.shaked.service.controllers;

import com.shaked.service.models.Article;
import com.shaked.service.repositories.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {


    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    
    @PostMapping(value = "/postImage/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpStatus postImage(@RequestParam(value = "image") MultipartFile image, @PathVariable Long id) throws IOException {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            article.setImage(image.getBytes());
            articleRepository.save(article);
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }


    @GetMapping("/article/image/{id}")
    public @ResponseBody
    ResponseEntity getImage(@PathVariable long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null && article.getImage() != null) {

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(article.getImage().length)
                    .body(article.getImage());
        }
        return null;
    }


}
