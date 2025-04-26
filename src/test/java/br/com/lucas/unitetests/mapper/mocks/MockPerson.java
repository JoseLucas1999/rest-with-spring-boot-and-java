package br.com.lucas.unitetests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.lucas.data.dto.v1.PersonDTO;
import br.com.lucas.model.Person;

//Essa classe serve para gerar objetos falsos (mocks) 
//de Person e PersonDTO, que você pode usar em testes unitários.
//deixa seus testes mais rápidos e organizados, 
//porque você não precisa ficar criando manualmente objetos Person e PersonDTO 
//em cada método de teste. Você apenas chama new MockPerson().mockEntity() ou 
//mockDTOList() e já tem dados prontos.

public class MockPerson {

//	Cria e retorna um único objeto Person de teste com dados padrão.
    public Person mockEntity() {
        return mockEntity(0);
    }
    
//    Cria e retorna um único objeto PersonDTO de teste com dados padrão.
    public PersonDTO mockDTO() {
        return mockDTO(0);
    }
    
//    Cria e retorna uma lista de 14 objetos Person de teste, numerados de 0 a 13 
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

//    Cria e retorna uma lista de 14 objetos PersonDTO, também variando os campos.
    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
//    mockEntity(Integer number) e mockDTO(Integer number)    
//    São métodos auxiliares que recebem um número para personalizar os campos
//    (First Name Test0, First Name Test1, etc.)
//    e alternar o gênero entre "Male" e "Female" dependendo se o número é par ou ímpar.
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}