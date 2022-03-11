package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.*;
import com.shaked.service.repositories.ContactRepository;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DgsComponent
@Component
@Slf4j
public class ContactCommandResolver {

    private final ContactRepository contactRepository;


    public ContactCommandResolver(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @DgsMutation(field = "createContact")
    public Contact createContact(@InputArgument ContactInput contactInput) {

        var contact = Contact.builder()
                .phoneNumber(contactInput.getPhoneNumber())
                .category(contactInput.getCategory())
                .address(contactInput.getAddress())
                .email(contactInput.getEmail())
                .date(new Date())
                .fullName(contactInput.getFullName())
                .build();

        contact = contactRepository.save(contact);
        if (contactInput.getSurvey() != null) {
            contact.setSurvey(parseSurvey(contactInput.getSurvey()));
            contactRepository.save(contact);
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

}
