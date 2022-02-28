package com.shaked.service.graphql.dataloaders;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.shaked.service.models.TextContainer;
import com.shaked.service.repositories.TextRepository;
import org.dataloader.MappedBatchLoader;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@DgsDataLoader(name = "texts")
public class TextDataLoader implements MappedBatchLoader<Long, TextContainer> {

    private final TextRepository repository;
    private final Executor executor;


    public TextDataLoader(TextRepository repository, Executor executor) {
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public CompletionStage<Map<Long, TextContainer>> load(Set<Long> ids) {
        return CompletableFuture.supplyAsync(() -> StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toMap(object -> object.getId(), object -> object)), executor);
    }

}
