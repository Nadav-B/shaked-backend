package com.shakedimportservicebackend.shakedimportservice.rest;

import com.shakedimportservicebackend.shakedimportservice.persistence.model.Answer;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Survey;

import com.shakedimportservicebackend.shakedimportservice.repo.ContactRepository;
import com.shakedimportservicebackend.shakedimportservice.repo.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Disabled
@ActiveProfiles("local")
public class SurveyTest {

    @Autowired
    private ContactController contactController;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SurveyRepository surveyRepository;


    @Test
    @Disabled
    void insertedSurvey() {

        Survey survey = Survey.builder().name("tmmmmest").build();
        Contact contact = Contact.builder().fullname("namdav").phonenumber("050").build();

        Survey survey1 = surveyRepository.save(survey);
        Contact contact1 = contactRepository.save(contact);
        contact1.setSurvey(survey1);
        contactRepository.save(contact1);
        // Contact contact1 = contactRepository.save(contact);
        //   survey.setContact(contact1);

        // Answer answer = Answer.builder().question("how are you today from 1-5?").answer("1").build();
        // contactRepository.save(contact1);

        Assertions.assertTrue(contactRepository.findAll().iterator().hasNext());
    }

    @Test
    void getAll() {

        Iterable<Contact> all = contactController.findAll();
        System.out.println(all);
        Assertions.assertTrue(all.iterator().hasNext());

    }
}
