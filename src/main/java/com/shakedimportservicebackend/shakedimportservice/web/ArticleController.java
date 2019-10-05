package com.shakedimportservicebackend.shakedimportservice.web;

import com.oracle.tools.packager.Log;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/articles")
@CrossOrigin()
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    @CrossOrigin
    public Iterable findAll() {
        return articleRepository.findAll();
    }

    @PostMapping("insert")
    @CrossOrigin
    public Article post(@RequestBody Article article) {
        article.setModificationDate(new Date());
        articleRepository.save(article);
        return article;
    }

    @PostMapping("update")
    @CrossOrigin
    public Article updateArticle(@RequestBody Article article) {

        Optional<Article> articleToModify = articleRepository.findById(article.getId());
        if (articleToModify.isPresent()) {
            articleToModify.get().modifyArticle(article);
            article.setModificationDate(new Date());
            articleRepository.save(articleToModify.get());
        }
        return article;

    }

    @GetMapping("delete/{id}")
    @CrossOrigin
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }


    @GetMapping("article/{id}")
    @CrossOrigin
    public Article findById(@PathVariable Long id) {
        Log.info(id.toString());
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }


}
