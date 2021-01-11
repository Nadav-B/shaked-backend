package com.shakedimportservicebackend.shakedimportservice.controllers;


import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.repositories.ContactRepository;
import com.shakedimportservicebackend.shakedimportservice.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    private SurveyRepository surveyRepository;

    @GetMapping
    public Iterable findAll() {
        return contactRepository.findAll();
    }


    @PostMapping("/post")
    @CrossOrigin
    public Contact addContact(@RequestBody Contact contact) {
        contact.setDate(new Date());
        return contactRepository.save(contact);
    }


    @GetMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteImage(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }


}
