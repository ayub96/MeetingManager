package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private Person mapFromDTO(PersonDTO model) {
		return this.mapper.map(model, Person.class);
	}
	
	public PersonDTO create(PersonDTO model) {
		Person toSave = this.mapFromDTO(model);
		Person saved = this.repo.save(toSave);
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
	
	public PersonDTO update(PersonDTO model, Long id) {
		Person toUpdate = this.repo.findById(id).orElseThrow();
		toUpdate.setFirstname(model.getFirstname());
		toUpdate.setSurname(model.getSurname());
		toUpdate.setEmail(model.getEmail());
		return this.mapToDTO(this.repo.save(toUpdate));
	}
}
