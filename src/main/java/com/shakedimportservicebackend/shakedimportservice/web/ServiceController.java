package com.shakedimportservicebackend.shakedimportservice.web;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Service;
import com.shakedimportservicebackend.shakedimportservice.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/services")
public class ServiceController {


    @Autowired
    private ServiceRepository serviceRepository;


    @GetMapping
    @CrossOrigin("${website.cros}")
    public Iterable findAll() {
        return serviceRepository.findAll();
    }

    @PostMapping
    public Service post(@RequestBody Service service) {
      //  service.setModificationDate(new Date());
        return serviceRepository.save(service);
    }

}
