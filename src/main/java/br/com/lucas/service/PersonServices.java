package br.com.lucas.service;

import static br.com.lucas.mapper.ObjectMapper.parseListObjects;
import static br.com.lucas.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.controller.PersonController;
import br.com.lucas.data.dto.v1.PersonDTO;
import br.com.lucas.data.dto.v2.PersonDTOV2;
import br.com.lucas.exeption.RequiredObjectIsNullException;
import br.com.lucas.exeption.ResourceNotFoundException;
import br.com.lucas.mapper.custom.PersonMapper;
import br.com.lucas.model.Person;
import br.com.lucas.repository.PersonRepository;

@Service
public class PersonServices {
	
//    private final AtomicLong counter = new AtomicLong();
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
        var people =  parseListObjects(repository.findAll(), PersonDTO.class);
//      people.forEach(p -> addHateoasLinks(p));
        people.forEach(this::addHateoasLinks);
        return people;
    }

  //found person by id
   /* public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }*/

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto =  parseObject(entity, PersonDTO.class); //a entidade Person é convertida para um objeto PersonDTO.
        addHateoasLinks(dto); //método para fornecer links
        return dto;
    }

	//create a person
    public PersonDTO create(PersonDTO person) {
//    	validar se person é null
//    	se for null lança uma exceção customizada RequiredObjectIsNullException
    	if(person == null) throw new RequiredObjectIsNullException();
    	
        logger.info("Creating one Person!");
     // Converte o DTO para a entidade
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    
    public PersonDTOV2 createV2(PersonDTOV2 person) {
    	logger.info("Creating one Person V2!");
    	// Converte o DTO para a entidade
    	var entity = converter.convertDTOtoEntity(person);
    	return converter.convertEntityToDTO(repository.save(entity));
    }

    //update a person
    public PersonDTO update(PersonDTO person) {
//    	validar se person é null
//    	se for null lança uma exceção customizada RequiredObjectIsNullException
    	if(person == null) throw new RequiredObjectIsNullException();
    	
        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto =  parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

	private void addHateoasLinks(PersonDTO dto) {
    	dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
    	dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
    	dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
    	dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
    	dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

//	É como se fosse um contador de números longos, 
//	mas preparado para ser usado em sistemas que têm muitas threads 
//	trabalhando ao mesmo tempo, sem precisar usar synchronized ou trancar 
//	manualmente o acesso.
	
//	public AtomicLong getCounter() {
//		return counter;
//	}

	
}

























