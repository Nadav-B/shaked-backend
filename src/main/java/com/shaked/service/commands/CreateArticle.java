package com.shaked.service.commands;

import com.shaked.service.models.Article;
import com.shaked.service.models.ArticleInput;
import com.shaked.service.repositories.ArticleRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateArticle implements Command {
    private final ArticleRepository repository;

    public CreateArticle(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article execute(ArticleInput data) {

        return repository.save(Article.builder()
                .contactButton(data.getContactButton())
                .content(data.getContent())
                .title(data.getTitle())
                .introduction(data.getIntroduction())
                .modificationDate(new Date())
                .tag(data.getTag())
                .mediaId(data.getMediaId())
                .build()
        );
    }

    @Override
    public Operation getName() {
        return Operation.CREATE_ARTICLE;
    }
}
