package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.TextDataLoader;
import com.shaked.service.models.TextContainer;
import com.shaked.service.repositories.TextRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class TextDataFetcher {

    private final TextRepository repository;

    public TextDataFetcher(TextRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "text")
    public CompletableFuture<TextContainer> getText(@InputArgument String id, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<String, TextContainer>getDataLoader(TextDataLoader.class);
        return dataLoader.load(id);
    }

    @DgsQuery(field = "texts")
    public List<TextContainer> getTexts() {
        return repository.findAll().stream().toList();

    }
}
