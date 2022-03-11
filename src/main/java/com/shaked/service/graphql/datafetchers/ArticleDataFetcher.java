package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.ArticleDataLoader;
import com.shaked.service.graphql.dataloaders.TextDataLoader;
import com.shaked.service.models.Article;
import com.shaked.service.models.TextContainer;

import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ArticleDataFetcher {
    @DgsQuery(field = "article")
    public CompletableFuture<Article> getArticle(@InputArgument Long id, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<Long, Article>getDataLoader(ArticleDataLoader.class);
        return dataLoader.load(id);
    }
}
