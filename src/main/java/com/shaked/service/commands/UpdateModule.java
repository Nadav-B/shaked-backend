package com.shaked.service.commands;

import com.shaked.service.excepctions.NotFoundException;
import com.shaked.service.models.Module;
import com.shaked.service.models.ModuleInput;
import com.shaked.service.repositories.ModuleRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateModule implements Command {

    private final ModuleRepository repository;

    public UpdateModule(ModuleRepository repository) {
        this.repository = repository;
    }

    public Module execute(ModuleInput data) {
        var module = repository.findById(Integer.valueOf(data.getId())).orElseThrow(() -> new NotFoundException(data.getId()));
        if (data.getContent() != null) module.setContent(data.getContent());
        if (data.getContactButton() != null) module.setContactButton(data.getContactButton());
        if (data.getTag() != null) module.setTag(data.getTag());
        if (data.getTitle() != null) module.setTitle(data.getTitle());
        if (data.getIntroduction() != null) module.setIntroduction(data.getIntroduction());
        if (data.getMediaId() != null) module.setMediaId(data.getMediaId());
        if (data.getType() != null) module.setType(data.getType());

        module.setUpdatedAt(new Date());
        return repository.save(module);
    }

    @Override
    public Operation getName() {
        return Operation.UPDATE_MODULE;
    }
}
