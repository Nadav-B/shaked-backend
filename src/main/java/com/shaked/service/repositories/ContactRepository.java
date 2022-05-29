package com.shaked.service.repositories;

import com.shaked.service.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Override
    List<Contact> findAll();

}
