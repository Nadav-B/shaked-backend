package com.shaked.service.repositories;

import com.shaked.service.models.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
}
