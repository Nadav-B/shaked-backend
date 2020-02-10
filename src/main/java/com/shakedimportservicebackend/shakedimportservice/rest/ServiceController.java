package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Service;
import com.shakedimportservicebackend.shakedimportservice.repo.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


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

    @PostMapping("/insert")
    public Service post(@RequestBody Service service) {
        //  service.setModificationDate(new Date());
        return serviceRepository.save(service);
    }

    @GetMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }

    @PostMapping("/update")
    public Service updateArticle(@RequestBody Service service) {

        Optional<Service> serviceToModify = serviceRepository.findById(service.getId());
        if (serviceToModify.isPresent()) {
            serviceToModify.get().modifyService(service);
            serviceRepository.save(serviceToModify.get());
        }
        return service;

    }

    @GetMapping("/service/{id}")
    public Service findById(@PathVariable Long id) {
        log.info(id.toString());
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unavailable"));

    }
}
