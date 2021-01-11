package com.shakedimportservicebackend.shakedimportservice.repositories;

import com.shakedimportservicebackend.shakedimportservice.models.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Override
    List<Offer> findAll();

    Optional<Offer> findByPath(String title);
}
