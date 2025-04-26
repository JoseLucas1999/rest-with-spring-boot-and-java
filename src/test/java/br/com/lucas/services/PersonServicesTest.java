package br.com.lucas.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.lucas.model.Person;
import br.com.lucas.repository.PersonRepository;
import br.com.lucas.service.PersonServices;
import br.com.lucas.unitetests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //ciclo de vida
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	MockPerson input; //class mockPerson que vai gerar dados falsos
	
//	não vai usar o banco de dados real, mas sim um objeto simulado.
	@Mock
    PersonRepository repository;
    
//	service vai chamar o repository, mas tudo acontece em ambiente simulado.
    @InjectMocks
    private PersonServices service;

//    Antes de cada teste inicializa o input para poder gerar dados de teste.
    @BeforeEach
    void setUp() {
    	input = new MockPerson();
    	MockitoAnnotations.openMocks(this);
    }
    
//    Esse setUp() prepara tudo o que o teste precisa para funcionar:
//    	Dados de entrada (MockPerson).
//    	Serviço (PersonServices) pronto com mocks injetados.
//    	Repositório fake (PersonRepository) para controlar as respostas.
//--------------------------------------------------------------------------------
    @Test
    void findById() {
    	Person person = input.mockEntity(1);
    	person.setId(1L);
    	when(repository.findById(1L)).thenReturn(Optional.of(person));
    	var result = service.findById(1L);
    	
    	assertNotNull(result);
    	assertNotNull(result.getId());
    	assertNotNull(result.getLinks());
    	
//    	links
    	assertNotNull(result.getLinks().stream()
    			.anyMatch(link -> link.getRel().value().equals("self")
    					&& link.getHref().endsWith("/api/person/v1/1")
    					&& link.getType().equals("GET")
    					)
    			);
//		findAll
    	assertNotNull(result.getLinks().stream()
    			.anyMatch(link -> link.getRel().value().equals("findAll")
    					&& link.getHref().endsWith("/api/person/v1")
    					&& link.getType().equals("GET")
    					)
    			);
//    	create
    	assertNotNull(result.getLinks().stream()
    			.anyMatch(link -> link.getRel().value().equals("create")
    					&& link.getHref().endsWith("/api/person/v1")
    					&& link.getType().equals("POST")
    					)
    			);

//    	update
    	assertNotNull(result.getLinks().stream()
    			.anyMatch(link -> link.getRel().value().equals("update")
    					&& link.getHref().endsWith("/api/person/v1")
    					&& link.getType().equals("PUT")
    					)
    			);

//    	delete
    	assertNotNull(result.getLinks().stream()
    			.anyMatch(link -> link.getRel().value().equals("delete")
    					&& link.getHref().endsWith("/api/person/v1/1")
    					&& link.getType().equals("DELETE")
    					)
    			);
    	
    	assertEquals("Address Test1", result.getAddress());
    	assertEquals("First Name Test1", result.getFirstName());
    	assertEquals("Last Name Test1", result.getLastName());
    	assertEquals("Female", result.getGender());
    }


	@Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {

    }
}