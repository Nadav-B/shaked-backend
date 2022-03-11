package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Service;
import com.shaked.service.repositories.ServiceRepository;

import java.util.List;


@DgsComponent
public class ServicesDataFetcher {

    private final ServiceRepository repository;

    public ServicesDataFetcher(ServiceRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "services")
    public List<Service> getServices() {
        return repository.findAll().stream().toList();

    }


}
