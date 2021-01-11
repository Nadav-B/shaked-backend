package com.shakedimportservicebackend.shakedimportservice.repositories;

import com.shakedimportservicebackend.shakedimportservice.models.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
