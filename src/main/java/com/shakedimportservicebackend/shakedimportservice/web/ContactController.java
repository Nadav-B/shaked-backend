package com.shakedimportservicebackend.shakedimportservice.web;


import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/contacts")
@CrossOrigin
@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    @CrossOrigin("${website.cros}")
    public Iterable findAll() {
        return contactRepository.findAll();
    }

    @PostMapping("addContact")
    @CrossOrigin
    public Contact addContact(@RequestBody Contact contact) {
        contact.setDate(new Date());
        contact.setMarkAsRead(false);
        return contactRepository.save(contact);
    }

    @GetMapping("deleteContact")
    public boolean deleteContact(@RequestBody long id) {
        Optional<Contact> contactToDelete = contactRepository.findById(id);
        if (contactToDelete.isPresent()) {
            contactRepository.delete(contactToDelete.get());
            return true;
        }
        return false;
    }

}
