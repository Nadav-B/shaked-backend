package com.shakedimportservicebackend.shakedimportservice.resolvers;


import com.shakedimportservicebackend.shakedimportservice.models.TextContainer;
import com.shakedimportservicebackend.shakedimportservice.repositories.TextRepository;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Component
@GraphQLApi
public class TextResolver {

    private final TextRepository textRepository;

    public TextResolver(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @GraphQLQuery
    public Optional<TextContainer> getText(@GraphQLNonNull final Long id) {
        return textRepository.findById(id);
    }

    @GraphQLQuery(name = "getTexts")
    @CrossOrigin
    public List<TextContainer> getTexts() {
        return textRepository.findAll();
    }
}