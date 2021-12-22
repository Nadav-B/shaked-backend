package com.shakedimportservicebackend.shakedimportservice.resolvers;


import com.shakedimportservicebackend.shakedimportservice.models.Article;
import com.shakedimportservicebackend.shakedimportservice.repositories.ArticleRepository;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@GraphQLApi
public class ArticleResolver {

    private final ArticleRepository articleRepository;

    public ArticleResolver(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GraphQLQuery
    public Optional<Article> getArticle(@GraphQLNonNull final Long id) {
        return articleRepository.findById(id);
    }

    @GraphQLQuery(name = "getArticles")
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}