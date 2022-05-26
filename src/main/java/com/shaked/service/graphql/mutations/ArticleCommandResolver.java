package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.*;
import com.shaked.service.repositories.ArticleRepository;
import com.shaked.service.repositories.ContactRepository;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DgsComponent
@Component
@Slf4j
public class ArticleCommandResolver {

    private final ArticleRepository repository;


    public ArticleCommandResolver(ArticleRepository repository) {
        this.repository = repository;
    }

    @DgsMutation
    public Article createArticle(@InputArgument ArticleInput data) {

        return Article.builder()
                .contactButton(data.getContactButton())
                .content(data.getContent())
                .introduction(data.getIntroduction())
                .modificationDate(new Date())
                .tag(data.getTag())
                .build();


    }

    @DgsMutation
    public String deleteArticle(@InputArgument String id) {
        repository.deleteById(Long.valueOf(id));
        return id;
    }


}
