package br.com.JoseLucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.JoseLucas.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
