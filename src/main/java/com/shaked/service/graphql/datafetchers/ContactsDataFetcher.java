package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Article;
import com.shaked.service.models.Contact;
import com.shaked.service.repositories.ArticleRepository;
import com.shaked.service.repositories.ContactRepository;
import org.springframework.security.access.annotation.Secured;

import java.util.List;


@DgsComponent
public class ContactsDataFetcher {

    private final ContactRepository repository;

    public ContactsDataFetcher(ContactRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "contacts")
    @Secured("ROLE_ADMIN")
    public List<Contact> getContacts() {
        return repository.findAll().stream().toList();

    }


}
