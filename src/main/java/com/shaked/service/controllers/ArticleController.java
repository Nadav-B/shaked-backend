package com.shaked.service.controllers;

import com.shaked.service.models.Article;
import com.shaked.service.repositories.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    @PostMapping("/post")
    public Article post(@RequestBody Article article) {
        if (article.getId() != null) {
            articleRepository.findById(article.getId()).ifPresent(storedArticle -> {
                        article.setImage(storedArticle.getImage());
                    } );
        }
        return articleRepository.save(article);
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


    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public HttpStatus deleteArticle(@PathVariable Long id) {
        if (id != null) {
            articleRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_MODIFIED;
    }

    @GetMapping("/article/{id}")
    public Article findById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }

}
