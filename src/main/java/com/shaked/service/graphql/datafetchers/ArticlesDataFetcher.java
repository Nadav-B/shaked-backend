package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Article;
import com.shaked.service.repositories.ArticleRepository;
import org.springframework.security.access.annotation.Secured;

import java.util.List;


@DgsComponent
public class ArticlesDataFetcher {

    private final ArticleRepository repository;

    public ArticlesDataFetcher(ArticleRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "articles")
    public List<Article> getArticles() {
        return repository.findAll().stream().toList();

    }


}
