package com.shaked.service.commands;

import com.shaked.service.models.*;
import com.shaked.service.repositories.ContactRepository;
import lombok.experimental.Tolerate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CreateContact implements Command {
    private final ContactRepository repository;

    public CreateContact(ContactRepository repository) {
        this.repository = repository;
    }

    public Contact execute(ContactInput data) {

        var contact = repository.save(Contact.builder()
                .phoneNumber(data.getPhoneNumber())
                .category(data.getCategory())
                .address(data.getAddress())
                .email(data.getEmail())
                .createdAt(new Date())
                .updatedAt(new Date())
                .fullName(data.getFullName())
                .build());

        if (data.getSurvey() != null) {
            contact.setSurvey(parseSurvey(data.getSurvey()));
            repository.save(contact);
        }

        return contact;
    }


    @Tolerate
    public Survey parseSurvey(SurveyInput surveyInput) {
        var survey = Survey.builder().name(surveyInput.getName()).build();

        List<Answer> answers = new ArrayList<>();
        surveyInput.getAnswers().forEach(answerInput ->
                answers.add(Answer.builder().survey(survey).answer(answerInput.getAnswer()).question(answerInput.getQuestion()).build()));

        survey.setAnswers(answers);
        return survey;

    }

    @Override
    public Operation getName() {
        return Operation.CREATE_CONTACT;
    }
}
