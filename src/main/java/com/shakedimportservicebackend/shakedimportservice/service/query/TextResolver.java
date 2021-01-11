package com.shakedimportservicebackend.shakedimportservice.service.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.TextContainer;
import com.shakedimportservicebackend.shakedimportservice.repositories.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Component
public class TextResolver implements GraphQLQueryResolver {

    @Autowired
    TextRepository textRepository;

    public Optional<TextContainer> getText(final Long id) {
        return textRepository.findById(id);
    }

    @CrossOrigin
    public List<TextContainer> getTexts() {
        return textRepository.findAll();
    }
}