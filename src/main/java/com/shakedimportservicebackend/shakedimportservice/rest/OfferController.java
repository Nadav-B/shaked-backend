package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Offer;
import com.shakedimportservicebackend.shakedimportservice.repo.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/offers")
@Slf4j
public class OfferController {


    @Autowired
    private OfferRepository offerRepository;

    @GetMapping
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }


    @PostMapping("/post")
    @Secured("ROLE_ADMIN")
    public Offer post(@RequestBody Offer offer) {
        return offerRepository.save(offer);
    }


    @GetMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public HttpStatus deleteArticle(@PathVariable Long id) {
        if (id != null) {
            offerRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_MODIFIED;
    }

    @GetMapping("/offer/{id}")
    public Offer findById(@PathVariable Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }

    @GetMapping("/offer/path/{path}")
    public Offer findByName(@PathVariable String path) {
        Offer offer = offerRepository.findByPath(path)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

        return offer;
    }
}
