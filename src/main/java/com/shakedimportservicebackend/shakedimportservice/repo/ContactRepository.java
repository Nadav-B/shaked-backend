package com.shakedimportservicebackend.shakedimportservice.repo;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
