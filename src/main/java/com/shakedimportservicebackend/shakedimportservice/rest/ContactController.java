package com.shakedimportservicebackend.shakedimportservice.rest;


import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.repo.ContactRepository;
import com.shakedimportservicebackend.shakedimportservice.repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;


@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    private SurveyRepository surveyRepository;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public Iterable findAll() {
        return contactRepository.findAll();
    }

    @PostMapping("/post")
    @CrossOrigin
    public Contact addContact(@RequestBody Contact contact) {
        contact.setDate(new Date());
        contact.setMarkAsRead(false);
        return contactRepository.save(contact);
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteImage(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }


}
