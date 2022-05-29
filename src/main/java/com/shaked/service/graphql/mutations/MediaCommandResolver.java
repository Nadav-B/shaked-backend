package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.DgsConstants;
import com.shaked.service.commands.Command;
import com.shaked.service.commands.CreateContact;
import com.shaked.service.commands.CreateMedia;
import com.shaked.service.commands.Operation;
import com.shaked.service.models.*;
import com.shaked.service.repositories.MediaRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
@Component
@Slf4j
public class MediaCommandResolver {

    private final MediaRepository repository;

    private final Map<Operation, Command> commands;


    public MediaCommandResolver(MediaRepository repository, List<Command> commands) {
        this.repository = repository;
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getName, command -> command));
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = "saveMedia")
    public Media saveMedia(DataFetchingEnvironment dfe) throws IOException {
        MultipartFile file = dfe.getArgument("data");
        return ((CreateMedia) commands.get(Operation.CREATE_CONTACT)).execute(file);
    }

    @DgsMutation
    @Secured("ROLE_ADMIN")
    public Media deleteMedia(@InputArgument String id) {
        repository.deleteById(Integer.valueOf(id));
        return Media.builder().id(Integer.valueOf(id)).build();
    }
}
