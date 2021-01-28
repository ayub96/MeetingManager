package com.qa.persistence.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class PersonDTO {

	private Long id;
	private String firstname;
	private String surname;
	private String email;
	
	
	public PersonDTO(Long id, String firstname, String surname, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	} 
	
	
	
}
