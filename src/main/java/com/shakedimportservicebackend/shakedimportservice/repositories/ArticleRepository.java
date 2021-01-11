package com.shakedimportservicebackend.shakedimportservice.repositories;

import com.shakedimportservicebackend.shakedimportservice.models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    List<Article> findAll();


}
