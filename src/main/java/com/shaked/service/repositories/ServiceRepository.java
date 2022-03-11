package com.shaked.service.repositories;

import com.shaked.service.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    @Override
    List<Service> findAll();

}
