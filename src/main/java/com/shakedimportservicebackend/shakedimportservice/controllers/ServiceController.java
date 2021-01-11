package com.shakedimportservicebackend.shakedimportservice.controllers;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Service;
import com.shakedimportservicebackend.shakedimportservice.repositories.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@Slf4j
@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public Iterable findAll() {
        return serviceRepository.findAll();
    }

    @PostMapping("/post")
    @Secured("ROLE_ADMIN")
    public Service post(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }


    @GetMapping("/service/{id}")
    public Service findById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }
}
