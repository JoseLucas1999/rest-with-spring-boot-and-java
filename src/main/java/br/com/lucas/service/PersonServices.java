package br.com.lucas.service;

import static br.com.lucas.mapper.ObjectMapper.parseListObjects;
import static br.com.lucas.mapper.ObjectMapper.parseObject;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.data.dto.v1.PersonDTO;
import br.com.lucas.data.dto.v2.PersonDTOV2;
import br.com.lucas.exeption.ResourceNotFoundException;
import br.com.lucas.mapper.custom.PersonMapper;
import br.com.lucas.model.Person;
import br.com.lucas.repository.PersonRepository;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());
    
    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper converter;

    //find all person 
    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");
//        return repository.findAll();
//        return ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

  //found person by id
    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

  //create a person
    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person!");
     // Converte o DTO para a entidade
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }
    
    public PersonDTOV2 createV2(PersonDTOV2 person) {
    	logger.info("Creating one Person V2!");
    	// Converte o DTO para a entidade
    	var entity = converter.convertDTOtoEntity(person);
    	return converter.convertEntityToDTO(repository.save(entity));
    }

    //update a person
    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

	public AtomicLong getCounter() {
		return counter;
	}
}