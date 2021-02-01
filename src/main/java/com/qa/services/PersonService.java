package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Utils.MyBeanUtils;
import com.qa.persistence.domain.Person;
import com.qa.persistence.dto.PersonDTO;
import com.qa.persistence.repo.PersonRepo;

@Service
public class PersonService {

	private PersonRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public PersonService(PersonRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private PersonDTO mapToDTO(Person model) {
		return this.mapper.map(model, PersonDTO.class);
	}
	
	public PersonDTO create(Person model) {
		Person saved = this.repo.save(model);
		return this.mapToDTO(saved);
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public PersonDTO read(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<PersonDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public PersonDTO update(Person person, Long id) {
		Person updatedPerson = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(person, updatedPerson);	// merges the properties between them including null gaps, ensures no errors occur due to null
		return this.mapToDTO(this.repo.save(updatedPerson));
	}
}
