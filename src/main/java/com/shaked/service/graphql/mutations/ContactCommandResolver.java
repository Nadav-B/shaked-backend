package com.shaked.service.graphql.mutations;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.shaked.service.models.Contact;
import com.shaked.service.models.ContactInput;
import com.shaked.service.models.Survey;
import com.shaked.service.repositories.ContactRepository;
import com.shaked.service.repositories.SurveyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@DgsComponent
@Component
@Slf4j
public class ContactCommandResolver {

    private final ContactRepository contactRepository;
    private final SurveyRepository surveyRepository;


    public ContactCommandResolver(ContactRepository contactRepository, SurveyRepository surveyRepository) {
        this.contactRepository = contactRepository;
        this.surveyRepository = surveyRepository;
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
            var survey = new Survey(contactInput.getSurvey());
            survey.setContact(contact);
            surveyRepository.save(survey);
        }
        return contact;
    }


}
