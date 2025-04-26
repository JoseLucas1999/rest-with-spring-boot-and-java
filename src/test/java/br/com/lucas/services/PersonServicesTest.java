package br.com.lucas.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lucas.data.dto.v1.PersonDTO;
import br.com.lucas.exeption.RequiredObjectIsNullException;
import br.com.lucas.model.Person;
import br.com.lucas.repository.PersonRepository;
import br.com.lucas.service.PersonServices;
import br.com.lucas.unitetests.mapper.mocks.MockPerson;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // ciclo de vida
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input; // class mockPerson que vai gerar dados falsos

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
				&& link.getType().equals("GET")));
//		findAll
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("GET")));
//    	create
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("POST")));

//    	update
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("PUT")));

//    	delete
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/1") 
				&& link.getType().equals("DELETE")));

		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

//    mudamos apenas os mocks e as chamadas
	@Test
	void create() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		persisted.setId(1L);
		PersonDTO dto = input.mockDTO(1);

		when(repository.save(person)).thenReturn(persisted);
		var result = service.create(dto);

		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

//    	links
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("self")
				&& link.getHref().endsWith("/api/person/v1/1") 
				&& link.getType().equals("GET")));
//		findAll
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("GET")));
//    	create
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("POST")));

//    	update
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("PUT")));

//    	delete
		assertNotNull(result.getLinks().stream()
				.anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/1") 
				&& link.getType().equals("DELETE")));

		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
//----------------------------------------------------------------------------------------
	
//	verificar se o método create() do service lança uma exceção quando recebe 
//	null como parâmetro.
	@Test
	void testCreateWithNullPerson() {
//		Aqui o teste espera que ao chamar service.create(null), seja lançada a 
//		exceção RequiredObjectIsNullException. esse método se encontra no package exception
//		Se não lançar essa exceção específica, o teste falha.
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
//		Define qual é a mensagem de erro esperada.
		String expectedMessage = "Is is not allowed to persist a null object!";
//		Pega a mensagem real que veio da exceção.
		String actualMessage = exception.getMessage();
		
//		Compara: verifica se a mensagem real contém a mensagem esperada.
//		Se contiver, o teste passa.
//		Se não contiver, o teste falha.
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	Esse teste garante que, se alguém tentar salvar um objeto null com o método create, 
//	o sistema vai reclamar corretamente com a exceção RequiredObjectIsNullException 
//	e com a mensagem certa."
	
//---------------------------------------------------------------------------------------

	@Test
	void update() {
		Person person = input.mockEntity(1);
		Person persisted = person;
		persisted.setId(1L);
		PersonDTO dto = input.mockDTO(1);

		when(repository.findById(1L)).thenReturn(Optional.of(person));
		when(repository.save(person)).thenReturn(persisted);

		var result = service.update(dto);

		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());

//    	links
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
				&& link.getHref().endsWith("/api/person/v1/1") 
				&& link.getType().equals("GET")));
//		findAll
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("GET")));
//    	create
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("POST")));

//    	update
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("PUT")));

//    	delete
		assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/1") 
				&& link.getType().equals("DELETE")));

		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
//-----------------------------------------------------------------------------------------	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "Is is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	adicione: 
//	if(person == null) throw new RequiredObjectIsNullException();
//-----------------------------------------------------------------------------------------	

//  delete não tem nenhum retorno
	@Test
	void delete() {
		Person person = input.mockEntity(1);
		person.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		service.delete(1L);

		verify(repository, times(1)).findById(anyLong());
		verify(repository, times(1)).delete(any(Person.class));
		verifyNoMoreInteractions(repository);

	}

	@Test
	void findAll() {
		List<Person> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		List<PersonDTO> people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personOne = people.get(1);
		
		assertNotNull(personOne);
		assertNotNull(personOne.getId());
		assertNotNull(personOne.getLinks());

//    	links
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
				&& link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));
//		findAll
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));
//    	create
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));

//    	update
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));

//    	delete
		assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

		assertEquals("Address Test1", personOne.getAddress());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());

		var personFour = people.get(4);
		
		assertNotNull(personFour);
		assertNotNull(personFour.getId());
		assertNotNull(personFour.getLinks());
		
//    	links
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
				&& link.getHref().endsWith("/api/person/v1/4") 
				&& link.getType().equals("GET")));
//		findAll
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("GET")));
//    	create
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("POST")));
		
//    	update
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1")
				&& link.getType().equals("PUT")));
		
//    	delete
		assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/4") 
				&& link.getType().equals("DELETE")));
		
		assertEquals("Address Test4", personFour.getAddress());
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Male", personFour.getGender());

		var personSeven = people.get(7);
		
		assertNotNull(personSeven);
		assertNotNull(personSeven.getId());
		assertNotNull(personSeven.getLinks());
		
//    	links
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
				&& link.getHref().endsWith("/api/person/v1/7") 
				&& link.getType().equals("GET")));
//		findAll
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("GET")));
//    	create
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
				&& link.getHref().endsWith("/api/person/v1") 
				&& link.getType().equals("POST")));
		
//    	update
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
				&& link.getHref().endsWith("/api/person/v1")
				&& link.getType().equals("PUT")));
		
//    	delete
		assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
				&& link.getHref().endsWith("/api/person/v1/7") 
				&& link.getType().equals("DELETE")));
		
		assertEquals("Address Test7", personSeven.getAddress());
		assertEquals("First Name Test7", personSeven.getFirstName());
		assertEquals("Last Name Test7", personSeven.getLastName());
		assertEquals("Female", personSeven.getGender());
	}
}





















