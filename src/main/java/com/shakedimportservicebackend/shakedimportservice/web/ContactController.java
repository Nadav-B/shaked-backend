package com.shakedimportservicebackend.shakedimportservice.web;


import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/contacts")
@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping

    public Iterable findAll() {
        return contactRepository.findAll();
    }

    @PostMapping
    public boolean saveAllContacts(@RequestBody Set<Contact> contacts) {
        try {
            contactRepository.saveAll(contacts);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("insert")
    public Contact addContact(@RequestBody Contact contact) {
        contact.setDate(new Date());
        contact.setMarkAsRead(false);
        return contactRepository.save(contact);
    }

    @PostMapping("update")
    public Contact updateArticle(@RequestBody Contact contact) {
        Optional<Contact> contactToModify = contactRepository.findById(contact.getId());
        if (contactToModify.isPresent()) {
            contactToModify.get().modifyContact(contact);
            contactRepository.save(contactToModify.get());
        }
        return contact;

    }

    @GetMapping("delete")
    public boolean deleteContact(@RequestBody long id) {
        Optional<Contact> contactToDelete = contactRepository.findById(id);
        if (contactToDelete.isPresent()) {
            contactRepository.delete(contactToDelete.get());
            return true;
        }
        return false;
    }

}
