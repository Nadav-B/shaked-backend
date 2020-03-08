package com.shakedimportservicebackend.shakedimportservice;


import com.shakedimportservicebackend.shakedimportservice.persistence.model.Contact;
import com.shakedimportservicebackend.shakedimportservice.persistence.model.Survey;
import com.shakedimportservicebackend.shakedimportservice.rest.ContactController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("local")
public class ShakedImportServiceApplicationTests {

	@Test
	public void contextLoads() {
	}



}
