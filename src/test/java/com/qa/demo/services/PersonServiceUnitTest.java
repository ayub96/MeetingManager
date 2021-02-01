package com.qa.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.SpringStarterApplication;
import com.qa.persistence.repo.PersonRepo;
import com.qa.services.PersonService;

@SpringBootTest(classes = SpringStarterApplication.class)
public class PersonServiceUnitTest {
	
	@Autowired
	private PersonService service;
	
	@MockBean
	private PersonRepo repoMock;
	
	
}
