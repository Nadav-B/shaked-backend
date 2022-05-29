package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.commands.Command;
import com.shaked.service.commands.CreateContact;
import com.shaked.service.commands.Operation;
import com.shaked.service.models.*;
import com.shaked.service.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
@Component
@Slf4j
public class ContactCommandResolver {

    private final ContactRepository repository;
    private final Map<Operation, Command> commands;

    public ContactCommandResolver(ContactRepository repository, List<Command> commands) {
        this.repository = repository;
        this.commands = commands.stream()
                .collect(Collectors.toMap(Command::getName, command -> command));
    }

    @DgsMutation
    public Contact saveContact(@InputArgument ContactInput data) {
        return ((CreateContact) commands.get(Operation.CREATE_CONTACT)).execute(data);
    }

    @DgsMutation
    @Secured("ROLE_ADMIN")
    public Contact deleteContact(@InputArgument String id) {
        repository.deleteById(Integer.valueOf(id));
        return Contact.builder().id(Integer.valueOf(id)).build();
    }


}
