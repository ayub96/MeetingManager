package com.qa.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.SpringStarterApplication;
import com.qa.persistence.domain.Person;
import com.qa.persistence.dto.PersonDTO;
import com.qa.persistence.repo.PersonRepo;
import com.qa.services.PersonService;

@SpringBootTest(classes = SpringStarterApplication.class)
public class PersonServiceUnitTest {
	
	@Autowired
	private PersonService service;
	
	@MockBean
	private PersonRepo repoMock;
	
	@Test
	public void createTest() {
		// Rules
		Mockito.when(this.repoMock.save(Mockito.any(Person.class))).thenReturn(new Person());
		// Action
		PersonDTO result = this.service.create(new Person());
		// Assertions & Verify
		Assertions.assertThat(result).isInstanceOf(PersonDTO.class);
		Mockito.verify(this.repoMock, Mockito.times(1)).save(new Person());
	}
	
	@Test
	public void readTest() {
		Person PersonTest = new Person(1L, "firstnameTest", "surnameTest", "emailTest");
		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(PersonTest));
		PersonDTO result = this.service.read(1L);
		PersonDTO personDTO = new PersonDTO(1L, "firstnameTest", "surnameTest", "emailTest");
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(personDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
		
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.repoMock.findAll()).thenReturn(new ArrayList<>());
		List<PersonDTO> result = this.service.readAll();
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(new ArrayList<>());
		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updateTest() {
		Person personTest = new Person(1L, "firstnameTest", "surnameTest", "emailTest");
		Person updatedPerson = new Person(1L, "firstnameTestUpdated", "surnameTestUpdated", "emailTestUpdated");
		
		PersonDTO updatedDTO = new PersonDTO(1L, "firstnameTestUpdated", "surnameTestUpdated", "emailTestUpdated");
		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(personTest));
		Mockito.when(this.repoMock.save(Mockito.any(Person.class))).thenReturn(updatedPerson);
		PersonDTO result = this.service.update(updatedPerson, 1L);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
		
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(this.repoMock.existsById(1L)).thenReturn(false);
		Assertions.assertThat(this.service.delete(1L)).isTrue();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void deleteTestFAIL() {
		Mockito.when(this.repoMock.existsById(1L)).thenReturn(true);
		Assertions.assertThat(this.service.delete(1L)).isFalse();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(1L);
	}
	
}
