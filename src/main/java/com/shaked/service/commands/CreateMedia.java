package com.shaked.service.commands;

import com.shaked.service.models.Media;
import com.shaked.service.repositories.MediaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Component
public class CreateMedia implements Command {
    private final MediaRepository repository;

    public CreateMedia(MediaRepository repository) {
        this.repository = repository;
    }

    public Media execute(MultipartFile file) throws IOException {
        return repository.save(Media.builder()
                .createdAt(new Date())
                .updatedAt(new Date())
                .fileName(file.getName())
                .content(file.getBytes())
                .build());
    }


    @Override
    public Operation getName() {
        return Operation.CREATE_MEDIA;
    }
}
