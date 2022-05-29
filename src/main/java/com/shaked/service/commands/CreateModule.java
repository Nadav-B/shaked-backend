package com.shaked.service.commands;

import com.shaked.service.models.Module;
import com.shaked.service.models.ModuleInput;
import com.shaked.service.repositories.ModuleRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateModule implements Command {
    private final ModuleRepository repository;

    public CreateModule(ModuleRepository repository) {
        this.repository = repository;
    }

    public Module execute(ModuleInput data) {

        return repository.save(Module.builder()
                .contactButton(data.getContactButton())
                .content(data.getContent())
                .title(data.getTitle())
                .introduction(data.getIntroduction())
                .createdAt(new Date())
                .tag(data.getTag())
                .type(data.getType())
                .mediaId(data.getMediaId())
                .build()
        );
    }

    @Override
    public Operation getName() {
        return Operation.CREATE_MODULE;
    }
}
