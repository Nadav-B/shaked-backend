package com.shaked.service.importer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaked.service.commands.CreateModule;
import com.shaked.service.models.ModuleInput;
import com.shaked.service.models.ModuleType;
import com.shaked.service.repositories.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ModulesImproter {

    private final Resource articles;
    private final Resource services;
    private final Resource about;

    private final CreateModule createModule;

    private final ModuleRepository moduleRepository;

    public ModulesImproter(
            @Value("classpath:data/articles.json") Resource articles,
            @Value("classpath:data/services.json") Resource services,
            @Value("classpath:data/about.json") Resource about,
            CreateModule createModule,
            ModuleRepository moduleRepository) {
        this.articles = articles;
        this.services = services;
        this.about = about;
        this.createModule = createModule;
        this.moduleRepository = moduleRepository;

        importArticles();
        importServices();
        importAbout();
    }


    private void importServices() {
        if (moduleRepository.existsByType(ModuleType.SERVICE)) return;
        log.info("Start importing services");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(services.getFile(), JsonNode.class);
            jsonNode.forEach(node -> {
                var moduleInput = ModuleInput.newBuilder()
                        .contactButton(node.get("contact_button").asText())
                        .introduction(node.get("introduction").asText())
                        .content(node.get("content").asText())
                        .type(ModuleType.SERVICE)
                        .title(node.get("title").asText())
                        .build();

                var module = createModule.execute(moduleInput);
                module.setId(Integer.valueOf(node.get("id").asText()));
                moduleRepository.save(module);
            });
        } catch (Exception e) {
            log.error("could not import", e);
        }
    }


    private void importAbout() {
        if (moduleRepository.existsByType(ModuleType.INTRODUCTION)) return;

        log.info("Start importing introduction");
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readValue(about.getFile(), JsonNode.class);
            jsonNode.forEach(node -> {
                var moduleInput = ModuleInput.newBuilder()
                        .content(node.get("content").asText())
                        .type(ModuleType.INTRODUCTION)
                        .build();

                var module = createModule.execute(moduleInput);
                module.setId(Integer.valueOf(node.get("id").asText()));
                moduleRepository.save(module);
            });
        } catch (Exception e) {
            log.error("could not import", e);
        }

    }

    private void importArticles() {
        if (moduleRepository.existsByType(ModuleType.ARTICLE)) return;
        log.info("Start importing articles");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(articles.getFile(), JsonNode.class);
            jsonNode.forEach(node -> {
                var moduleInput = ModuleInput.newBuilder()
                        .contactButton(node.get("contact_button").asText())
                        .introduction(node.get("introduction").asText())
                        .tag(node.get("tag").asText())
                        .content(node.get("content").asText())
                        .type(ModuleType.ARTICLE)
                        .title(node.get("title").asText())
                        .build();
                var module = createModule.execute(moduleInput);
                module.setId(Integer.valueOf(node.get("id").asText()));
                moduleRepository.save(module);
            });
        } catch (Exception e) {
            log.error("could not import", e);
        }

    }
}
