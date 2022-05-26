package com.shaked.service.repositories;

import com.shaked.service.models.Media;
import com.shaked.service.models.TextContainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaRepository extends CrudRepository<Media, Long> {

    List<Media> findAll();

}
