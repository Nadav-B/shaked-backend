package com.shaked.service.graphql.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.shaked.service.models.Media;
import com.shaked.service.models.TextContainer;
import com.shaked.service.repositories.MediaRepository;
import org.springframework.util.StreamUtils;

import java.util.List;
import java.util.stream.Stream;

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
