package com.shaked.service.graphql.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Media;
import com.shaked.service.repositories.MediaRepository;

import java.util.List;

@DgsComponent
public class MediaDataFetcher {
    private final MediaRepository repository;

    public MediaDataFetcher(MediaRepository repository) {
        this.repository = repository;
    }

    @DgsQuery(field = "media")
    public List<Media> getMedia() {
        return repository.findAll();
    }
}
