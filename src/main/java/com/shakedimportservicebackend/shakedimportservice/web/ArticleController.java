package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/articles")
//@CrossOrigin(origins = "http://10.100.102.20:9090")

public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping
    public Iterable findAll() {
        return articleRepository.findAll();
    }

    @PostMapping
    public Article post(@RequestBody Article article) {
        article.setModificationDate(new Date());
        return articleRepository.save(article);
    }



}
