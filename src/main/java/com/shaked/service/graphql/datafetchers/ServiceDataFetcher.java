package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.ServiceDataLoader;
import com.shaked.service.models.Service;
import com.shaked.service.repositories.ServiceRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ServiceDataFetcher {

    private final ServiceRepository repository;

    public ServiceDataFetcher(ServiceRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "service")
    public CompletableFuture<Service> getService(@InputArgument String id, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<Long, Service>getDataLoader(ServiceDataLoader.class);
        return dataLoader.load(Long.valueOf(id));
    }

    @DgsQuery(field = "services")
    public List<Service> getServices() {
        return repository.findAll().stream().toList();

    }

}
