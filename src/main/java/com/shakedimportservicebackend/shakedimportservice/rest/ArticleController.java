package com.shakedimportservicebackend.shakedimportservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("/api/articles")
@CrossOrigin
@Slf4j
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public Iterable findAll() {
        return articleRepository.findAll();
    }

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Article post(@RequestParam(value = "json") String json, @RequestParam(required = false, value = "file") MultipartFile file) throws IOException {
        Article article = new ObjectMapper().readValue(json, Article.class);
        article.setModificationDate(new Date());
        if (file != null) {
            try {
                article.setImage(file.getBytes());
            } catch (IOException e) {
                log.error("can't set image");
            }
        }
        articleRepository.save(article);
        return article;
    }


    @PostMapping(value = "update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Article update(@RequestParam(value = "json") String json, @RequestParam(required = false, value = "file") MultipartFile file) throws IOException {
        Article article = new ObjectMapper().readValue(json, Article.class);
        articleRepository.findById(article.getId()).ifPresent(articleToModify -> {
                    articleToModify.modifyArticle(article);
                    if (file != null) {
                        try {
                            articleToModify.setImage(file.getBytes());
                        } catch (IOException e) {
                            log.error("can't set image");
                        }
                    }
                    articleRepository.save(articleToModify);
                }
        );
        return article;
    }


    @GetMapping("delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }

    @GetMapping("article/{id}")
    public Article findById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }

}
