package com.shakedimportservicebackend.shakedimportservice.repo;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User,Long> {
}

