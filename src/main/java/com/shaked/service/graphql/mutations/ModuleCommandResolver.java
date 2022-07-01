package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.commands.Command;
import com.shaked.service.commands.CreateModule;
import com.shaked.service.commands.Operation;
import com.shaked.service.commands.UpdateModule;
import com.shaked.service.models.*;
import com.shaked.service.models.Module;
import com.shaked.service.repositories.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
@Component
@Slf4j
public class ModuleCommandResolver {

    private final ModuleRepository repository;

    private final Map<Operation, Command> commands;


    public ModuleCommandResolver(ModuleRepository repository, List<Command> commands) {
        this.repository = repository;
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getName, command -> command));

    }

    @DgsMutation
    @Secured("ROLE_ADMIN")
    public Module saveModule(@InputArgument ModuleInput data) {
        log.info(data.toString());
        if (data.getId() != null ) {
            return ((UpdateModule) commands.get(Operation.UPDATE_MODULE)).execute(data);
        } else {
            return ((CreateModule) commands.get(Operation.CREATE_MODULE)).execute(data);
        }
    }

    @DgsMutation
    @Secured("ROLE_ADMIN")
    public String deleteModule(@InputArgument String id) {
        repository.deleteById(Integer.valueOf(id));
        return id;
    }


}
