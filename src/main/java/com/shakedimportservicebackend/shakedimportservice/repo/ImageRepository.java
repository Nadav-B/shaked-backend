package com.shakedimportservicebackend.shakedimportservice.repo;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {


}