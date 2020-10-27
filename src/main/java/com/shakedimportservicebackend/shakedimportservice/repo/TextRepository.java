package com.shakedimportservicebackend.shakedimportservice.repo;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.TextContainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends CrudRepository<TextContainer, Long> {

    @Override
    List<TextContainer> findAll();
}
