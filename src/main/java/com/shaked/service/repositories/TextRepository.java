package com.shaked.service.repositories;

import com.shaked.service.models.TextContainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends CrudRepository<TextContainer, Long> {

    @Override
    List<TextContainer> findAll();
}
