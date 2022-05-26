package com.shaked.service.commands;

import com.shaked.service.excepctions.NotFoundException;
import com.shaked.service.models.Article;
import com.shaked.service.models.ArticleInput;
import com.shaked.service.repositories.ArticleRepository;

public class UpdateArticle implements Command {


    private final ArticleRepository repository;

    public UpdateArticle(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article execute(ArticleInput data) {
        var article = repository.findById(Long.valueOf(data.getId())).orElseThrow(() -> new NotFoundException(data.getId()));
        if (data.getHtml() != null) article.setContent(data.getHtml());
        if (data.getContactButton() != null) article.setContactButton(data.getContactButton());
        if (data.getTag() != null) article.setTag(data.getTag());
        if (data.getTitle() != null) article.setTitle(data.getTitle());
        if (data.getIntroduction() != null) article.setIntroduction(data.getIntroduction());

        return repository.save(article);
    }

    @Override
    public Operation getName() {
        return Operation.CREATE_ARTICLE;
    }
}
