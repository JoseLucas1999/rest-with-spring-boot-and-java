package br.com.JoseLucas.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.JoseLucas.exeption.ResourceNotFoundException;
import br.com.JoseLucas.model.Person;
import br.com.JoseLucas.repository.PersonRepository;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    //found all person 
    public List<Person> findAll() {
        logger.info("Finding all People!");
        return repository.findAll();
    }

  //found person by id
    public Person findById(Long id) {
        logger.info("Finding one Person!");
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

  //create a person
    public Person create(Person person) {
        logger.info("Creating one Person!");
        return repository.save(person);
    }

    //update a person
    public Person update(Person person) {
        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}