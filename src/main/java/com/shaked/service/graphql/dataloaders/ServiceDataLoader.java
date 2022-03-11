package com.shaked.service.graphql.dataloaders;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.shaked.service.models.Service;
import com.shaked.service.repositories.ServiceRepository;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DgsDataLoader(name = "services")
@Component
public class ServiceDataLoader implements MappedBatchLoader<Long, Service> {

    private final ServiceRepository repository;
    private final Executor executor;

    public ServiceDataLoader(ServiceRepository repository, Executor executor) {
        this.repository = repository;
        this.executor = executor;
    }


    @Override
    public CompletionStage<Map<Long, Service>> load(Set<Long> ids) {
        return CompletableFuture.supplyAsync(() -> StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toMap(object -> object.getId(), object -> object)), executor);
    }

}
