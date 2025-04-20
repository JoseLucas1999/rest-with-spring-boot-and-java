package br.com.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucas.data.dto.PersonDTO;
import br.com.lucas.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	Object save(PersonDTO entity);

}
