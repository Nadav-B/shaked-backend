package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.TextContainer;
import com.shakedimportservicebackend.shakedimportservice.repo.TextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@RestController
@RequestMapping("/api/texts")
@CrossOrigin()
@Slf4j
public class TextController {


    @Autowired
    private TextRepository textRepository;

    @GetMapping
    @CrossOrigin
    public Iterable findAll() {
        return textRepository.findAll();
    }

    @PostMapping("insert")
    @CrossOrigin
    public TextContainer post(@RequestBody TextContainer article) {
        textRepository.save(article);
        return article;
    }

    @PostMapping("update")
    @CrossOrigin
    public TextContainer updateText(@RequestBody TextContainer text) {

        Optional<TextContainer> articleToModify = textRepository.findById(text.getId());
        if (articleToModify.isPresent()) {
            articleToModify.get().modifyInformation(text);
            textRepository.save(articleToModify.get());
        }
        return text;

    }

    @GetMapping("delete/{id}")
    @CrossOrigin
    public void deleteArticle(@PathVariable Long id) {
        textRepository.deleteById(id);
    }


    @GetMapping("text/{id}")
    @CrossOrigin
    public TextContainer findById(@PathVariable Long id) {
        log.info(id.toString());
        return textRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));
    }


}
