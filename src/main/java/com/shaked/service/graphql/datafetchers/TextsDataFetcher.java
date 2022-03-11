package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Service;
import com.shaked.service.models.TextContainer;
import com.shaked.service.repositories.ServiceRepository;
import com.shaked.service.repositories.TextRepository;

import java.util.List;


@DgsComponent
public class TextsDataFetcher {

    private final TextRepository repository;

    public TextsDataFetcher(TextRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "texts")
    public List<TextContainer> getTexts() {
        return repository.findAll().stream().toList();

    }


}
