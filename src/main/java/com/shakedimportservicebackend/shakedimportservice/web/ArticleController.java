package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/articles")
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping
    @CrossOrigin("${website.cros}")
    public Iterable findAll() {


        return articleRepository.findAll();
    }

    @PostMapping
    public Article post(@RequestBody Article article) {
        article.setModificationDate(new Date());
        return articleRepository.save(article);
    }



}
