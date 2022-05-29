package com.shaked.service.graphql.dataloaders;


import com.netflix.graphql.dgs.DgsDataLoader;
import com.shaked.service.models.Module;
import com.shaked.service.repositories.ModuleRepository;
import org.dataloader.MappedBatchLoader;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@DgsDataLoader(name = "articles")
public class ModuleDataLoader implements MappedBatchLoader<Integer, Module> {

    private final ModuleRepository repository;
    private final Executor executor;

    public ModuleDataLoader(ModuleRepository repository, Executor executor) {
        this.repository = repository;
        this.executor = executor;
    }


    @Override
    public CompletionStage<Map<Integer, Module>> load(Set<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toMap(object -> object.getId(), object -> object)), executor);
    }


}
