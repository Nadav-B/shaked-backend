package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.commands.Command;
import com.shaked.service.commands.CreateArticle;
import com.shaked.service.commands.Operation;
import com.shaked.service.commands.UpdateArticle;
import com.shaked.service.models.*;
import com.shaked.service.repositories.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
@Component
@Slf4j
public class ArticleCommandResolver {

    private final ArticleRepository repository;

    private final Map<Operation, Command> commands;


    public ArticleCommandResolver(ArticleRepository repository, List<Command> commands) {
        this.repository = repository;
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getName, command -> command));

    }

    @DgsMutation
    @Secured("ROLE_ADMIN")
    public Article saveArticle(@InputArgument ArticleInput data) {
        if (data.getId() != null ) {
            return ((UpdateArticle) commands.get(Operation.UPDATE_ARTICLE)).execute(data);
        } else {
            return ((CreateArticle) commands.get(Operation.CREATE_ARTICLE)).execute(data);
        }
    }

    @DgsMutation
    public String deleteArticle(@InputArgument String id) {
        repository.deleteById(Long.valueOf(id));
        return id;
    }


}
