package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.ArticleDataLoader;
import com.shaked.service.models.Article;
import com.shaked.service.repositories.ArticleRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ArticleDataFetcher {

    private final ArticleRepository repository;

    public ArticleDataFetcher(ArticleRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "article")
    public CompletableFuture<Article> getArticle(@InputArgument String id, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<Long, Article>getDataLoader(ArticleDataLoader.class);
        return dataLoader.load(Long.valueOf(id));
    }

    @DgsQuery(field = "articles")
    public List<Article> getArticles() {
        return repository.findAll().stream().toList();

    }
}
