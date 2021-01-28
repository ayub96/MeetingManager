package com.qa.persistence.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
		private Long id;
		String roomType;
		String roomNumber;
		private List<PersonDTO> people;
}
