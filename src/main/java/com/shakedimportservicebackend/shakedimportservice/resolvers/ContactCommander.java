package com.shakedimportservicebackend.shakedimportservice.resolvers;


import com.shakedimportservicebackend.shakedimportservice.models.Contact;
import com.shakedimportservicebackend.shakedimportservice.repositories.ContactRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Component
@GraphQLApi
public class ContactCommander {

    private final ContactRepository contactRepository;

    public ContactCommander(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GraphQLMutation
    @CrossOrigin
    public Contact saveContact(@GraphQLNonNull Contact contact) {
        contact.setDate(new Date());
        return contactRepository.save(contact);
    }
}