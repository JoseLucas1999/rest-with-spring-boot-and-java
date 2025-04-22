package br.com.lucas.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.lucas.data.dto.v2.PersonDTOV2;
import br.com.lucas.model.Person;

@Service
public class PersonMapper {
	
//	o campo birthDay sóexiste no PersonDTOV2 por enquando
//	ele só irá ser criado no banco quando usuarios estiverem usando
	
	public PersonDTOV2 convertEntityToDTO(Person person) {
		PersonDTOV2 dto = new PersonDTOV2();
		dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
//		atributo só existirá no banco quando cliente estiver usando
//      e por isso setnew Date()
        dto.setBirthDay(new Date());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
		return dto;
	}
	
//	código ao contrario
	public Person convertDTOtoEntity(PersonDTOV2 person) {
		Person dto = new Person();
		dto.setId(person.getId());
		dto.setFirstName(person.getFirstName());
		dto.setLastName(person.getLastName());
//      por hora não precisamos retornar esse campo, pois elenão existe no banco
//		dto.setBirthDay(new Date());
		dto.setAddress(person.getAddress());
		dto.setGender(person.getGender());
		return dto;
	}
}
