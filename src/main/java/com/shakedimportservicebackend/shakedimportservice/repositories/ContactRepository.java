package com.shakedimportservicebackend.shakedimportservice.repositories;

import com.shakedimportservicebackend.shakedimportservice.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
