package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.Contact;
import com.shaked.service.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@DgsComponent
@Component
@Slf4j
public class ContactCommandResolver {

    private final ContactRepository contactRepository;


    public ContactCommandResolver(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @DgsMutation
    public Contact createContact(@InputArgument Contact contact) {
        contact.setDate(new Date());
        return contactRepository.save(contact);
    }

}
