package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Person;
import com.qa.persistence.dto.PersonDTO;
import com.qa.services.PersonService;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

	private PersonService service;
	
	@Autowired
	public PersonController(PersonService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<PersonDTO> createPerson(@RequestBody Person model){
		return new ResponseEntity<>(this.service.create(model), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PersonDTO> deletePerson(@PathVariable Long id){
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<PersonDTO> readPerson(@PathVariable Long id){
		return ResponseEntity.ok(this.service.read(id));
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<PersonDTO>> readAllPersons(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") Long id, @RequestBody Person model){
		return new ResponseEntity<>(this.service.update(model, id), HttpStatus.ACCEPTED);
	}
	
}
