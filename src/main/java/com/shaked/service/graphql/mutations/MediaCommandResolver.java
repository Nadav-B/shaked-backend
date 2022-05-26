package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.shaked.service.DgsConstants;
import com.shaked.service.models.*;
import com.shaked.service.repositories.MediaRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@DgsComponent
@Component
@Slf4j
public class MediaCommandResolver {

    private final MediaRepository mediaRepository;

    public MediaCommandResolver(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = "saveMedia")
    public Media saveMedia(DataFetchingEnvironment dfe) throws IOException {
        // NOTE: Cannot use @InputArgument  or Object Mapper to convert to class, because MultipartFile cannot be
        // deserialized
        MultipartFile file = dfe.getArgument("data");
        return mediaRepository.save(Media.builder()
                .fileName(file.getName())
                .content(file.getBytes())
                .build());
    }
}
