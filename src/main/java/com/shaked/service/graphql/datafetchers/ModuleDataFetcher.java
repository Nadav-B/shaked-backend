package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.ModuleDataLoader;
import com.shaked.service.models.Module;
import com.shaked.service.models.ModuleUniqueInput;
import com.shaked.service.models.ModuleWhereInput;
import com.shaked.service.repositories.ModuleRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ModuleDataFetcher {

    private final ModuleRepository repository;

    public ModuleDataFetcher(ModuleRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "module")
    public CompletableFuture<Module> getModule(@InputArgument ModuleUniqueInput where, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<Integer, Module>getDataLoader(ModuleDataLoader.class);
        return dataLoader.load(Integer.valueOf(where.getId()));
    }

    @DgsQuery(field = "modules")
    public List<Module> getModules(@InputArgument ModuleWhereInput where) {
        if (where !=null && where.getType() != null) return repository.findAllByType(where.getType());
        return repository.findAll().stream().toList();

    }
}
