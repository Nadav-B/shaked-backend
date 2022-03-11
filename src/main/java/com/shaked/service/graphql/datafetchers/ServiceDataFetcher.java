package com.shaked.service.graphql.datafetchers;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.graphql.dataloaders.ServiceDataLoader;
import com.shaked.service.models.Service;

import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ServiceDataFetcher {
    @DgsQuery(field = "service")
    public CompletableFuture<Service> getService(@InputArgument Long id, DgsDataFetchingEnvironment env) {
        var dataLoader = env.<Long, Service>getDataLoader(ServiceDataLoader.class);
        return dataLoader.load(id);
    }
}
