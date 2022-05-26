package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.Contact;
import com.shaked.service.repositories.ContactRepository;
import org.springframework.security.access.annotation.Secured;

import java.util.List;


@DgsComponent
public class ContactDataFetcher {

    private final ContactRepository repository;

    public ContactDataFetcher(ContactRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "contacts")
    @Secured("ROLE_ADMIN")
    public List<Contact> getContacts() {
        return repository.findAll().stream().toList();
    }


    @DgsQuery(field = "contact")
    @Secured("ROLE_ADMIN")
    public Contact getContact(@InputArgument String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow();
    }


}
