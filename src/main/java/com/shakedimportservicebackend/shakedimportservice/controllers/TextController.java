package com.shakedimportservicebackend.shakedimportservice.controllers;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.TextContainer;
import com.shakedimportservicebackend.shakedimportservice.repositories.TextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@Slf4j
@RestController
@RequestMapping("/texts")
public class TextController {


    @Autowired
    private TextRepository textRepository;

    @GetMapping
    public Iterable findAll() {
        return textRepository.findAll();
    }

    @PostMapping("/post")
    @Secured("ROLE_ADMIN")
    public TextContainer post(@RequestBody TextContainer textContainer) {
        textRepository.save(textContainer);
        return textContainer    ;
    }


    @GetMapping("delete/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteArticle(@PathVariable Long id) {
        textRepository.deleteById(id);
    }


    @GetMapping("text/{id}")
    public TextContainer findById(@PathVariable Long id) {
        return textRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));
    }


}
