package com.shaked.service.repositories;

import com.shaked.service.models.Module;
import com.shaked.service.models.ModuleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Integer> {
    @Override
    List<Module> findAll();

    List<Module> findAllByType(ModuleType moduleType);

    Boolean existsByType(ModuleType moduleType);


}
