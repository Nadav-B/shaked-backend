package com.shakedimportservicebackend.shakedimportservice.service.datafetcher;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;

@Component
public class ArticlesDataFetcher implements DataFetcher<List<Article>> {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return articleRepository.findAll();
    }
}