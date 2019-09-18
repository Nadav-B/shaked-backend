package com.shakedimportservicebackend.shakedimportservice.web;

import com.oracle.tools.packager.Log;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


@RestController
@RequestMapping("/articles")
@CrossOrigin("${website.cros}")
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    @CrossOrigin("${website.cros}")
    public Iterable findAll() {
        return articleRepository.findAll();
    }

    @PostMapping("addArticle")
    public Article post(@RequestBody Article article) {
        article.setModificationDate(new Date());
        return articleRepository.save(article);
    }

    @GetMapping("article/{id}")
    @CrossOrigin
    public Article findById(@PathVariable Long id){
        Log.info(id.toString());

        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }



}
