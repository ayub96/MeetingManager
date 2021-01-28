package com.qa.persistence.domain;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String surname;
	
	@NotNull
	private String email;
	
//	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private List<Appointment> appointments;
	
	@ManyToOne
	private Room room;
	
	public Person(Long id, String firstname, String surname, String email) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	}
	
	public Person(String firstname, String surname, String email) {
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
	}

	
}
