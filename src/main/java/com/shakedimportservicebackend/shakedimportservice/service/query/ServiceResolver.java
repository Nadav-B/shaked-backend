package com.shakedimportservicebackend.shakedimportservice.service.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Service;
import com.shakedimportservicebackend.shakedimportservice.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceResolver implements GraphQLQueryResolver {

    @Autowired
    ServiceRepository serviceRepository;

    public Optional<Service> getService(final Long id) {
        return serviceRepository.findById(id);
    }

    @CrossOrigin
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }
}