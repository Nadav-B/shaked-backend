package com.shakedimportservicebackend.shakedimportservice.resolvers;

import com.shakedimportservicebackend.shakedimportservice.models.Service;
import com.shakedimportservicebackend.shakedimportservice.repositories.ServiceRepository;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@GraphQLApi
public class ServiceResolver {

    private final ServiceRepository serviceRepository;

    public ServiceResolver(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GraphQLQuery
    public Optional<Service> getService(@GraphQLNonNull final Long id) {
        return serviceRepository.findById(id);
    }

    @GraphQLQuery(name = "getServices")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }
}