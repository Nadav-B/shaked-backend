package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.TextContainer;
import com.shakedimportservicebackend.shakedimportservice.repo.TextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


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

    @PostMapping("/insert")
    public TextContainer post(@RequestBody TextContainer article) {
        textRepository.save(article);
        return article;
    }

    @PostMapping("/update")
    public TextContainer updateText(@RequestBody TextContainer text) {

        Optional<TextContainer> articleToModify = textRepository.findById(text.getId());
        if (articleToModify.isPresent()) {
            articleToModify.get().modifyInformation(text);
            textRepository.save(articleToModify.get());
        }
        return text;

    }

    @GetMapping("delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        textRepository.deleteById(id);
    }


    @GetMapping("text/{id}")
    public TextContainer findById(@PathVariable Long id) {
        return textRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));
    }


}
