package com.shakedimportservicebackend.shakedimportservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Article post(@RequestParam(required = false, value = "json") String json,
                        @RequestParam(required = false, value = "textFile") MultipartFile textFile,
                        @RequestParam(required = false, value = "imageFile") MultipartFile imageFile) throws IOException {
        Article article = new ObjectMapper().readValue(json, Article.class);
        article.setModificationDate(new Date());

        if (textFile != null) {
            String content = new String(textFile.getBytes());
            article.setContent(content);
        }
        if (imageFile != null) {
            article.setImage(imageFile.getBytes());
        }
        articleRepository.save(article);
        return article;
    }


    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Article update(@RequestParam(value = "json") String json,
                          @RequestParam(required = false, value = "textFile") MultipartFile textFile,
                          @RequestParam(required = false, value = "imageFile") MultipartFile imageFile) throws IOException {
        Article article = new ObjectMapper().readValue(json, Article.class);
        articleRepository.findById(article.getId()).ifPresent(articleToModify -> {
                    articleToModify.modifyArticle(article);
                    if (imageFile != null) {
                        try {
                            articleToModify.setImage(imageFile.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (textFile != null) {
                        String content = null;
                        try {
                            content = new String(textFile.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        articleToModify.setContent(content);
                    }
                    articleRepository.save(articleToModify);
                }
        );
        return article;
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


    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }

    @GetMapping("/article/{id}")
    public Article findById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }

}
