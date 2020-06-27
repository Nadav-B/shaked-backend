package com.shakedimportservicebackend.shakedimportservice.service.datafetcher;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Article;
import com.shakedimportservicebackend.shakedimportservice.repo.ArticleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleDataFetcher implements DataFetcher<Article> {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = dataFetchingEnvironment.getArgument("id");
        return articleRepository.findById(id).get();
    }
}