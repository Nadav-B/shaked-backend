package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.Article;
import com.shaked.service.repositories.ArticleRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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
