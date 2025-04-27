package br.com.lucas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.data.dto.v1.PersonDTO;
import br.com.lucas.data.dto.v2.PersonDTOV2;
import br.com.lucas.service.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1") //http://localhost:8080/api/person/v1
@Tag(name = "People", description = "Endpoints for managing People")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices service;
    // private PersonServices service = new PersonServices();

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	//FIND ALL
    
    @Override //esse overide é da interface de docs do swagger
	@GetMapping(
    		produces = {MediaType.APPLICATION_JSON_VALUE, 
    					MediaType.APPLICATION_XML_VALUE,
    					MediaType.APPLICATION_YAML_VALUE
    					})
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
   //FIND BY ID
    
    @Override
	@GetMapping(value = "/{id}", 
    		produces = {MediaType.APPLICATION_JSON_VALUE, 
    					MediaType.APPLICATION_XML_VALUE,
    					MediaType.APPLICATION_YAML_VALUE
    					})
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------
    /*
//    apenas para simular JsonPatter
//    fazemos essa gambiara pois não temos o campo birthDay e setPhoneNumber no banco
//    esse é método é para realizar alguns testes
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id) {
    	var person =  service.findById(id);
    	person.setBirthDay(new Date());
    	person.setPhoneNumber(""); //não será redenrizado pois é empty
//    	person.setPhoneNumber("+55(16)99798-6230");
    	person.setLastName(null); //não será redenrizado pois é null
    	person.setSensitiveData("Foo Bar");
    	return person;
    }*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE
    
    @Override
	@PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, 
        			MediaType.APPLICATION_XML_VALUE,
        			MediaType.APPLICATION_YAML_VALUE
        		},
        produces = {MediaType.APPLICATION_JSON_VALUE, 
        			MediaType.APPLICATION_XML_VALUE,
        			MediaType.APPLICATION_YAML_VALUE}
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE V2
    
//  versão personDTOV2
  @PostMapping(value = "/v2",
      consumes = {MediaType.APPLICATION_JSON_VALUE, 
      			MediaType.APPLICATION_XML_VALUE,
      			MediaType.APPLICATION_YAML_VALUE
      		},
      produces = {MediaType.APPLICATION_JSON_VALUE, 
      			MediaType.APPLICATION_XML_VALUE,
      			MediaType.APPLICATION_YAML_VALUE}
  		)
  public PersonDTOV2 createv2(@RequestBody PersonDTOV2 person) {
  	return service.createV2(person);
  }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
  //UPDATE  
  
  @Override
	@PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, 
        			MediaType.APPLICATION_XML_VALUE,
        			MediaType.APPLICATION_YAML_VALUE
        		},
        produces = {MediaType.APPLICATION_JSON_VALUE, 
        			MediaType.APPLICATION_XML_VALUE,
        			MediaType.APPLICATION_YAML_VALUE}
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
    //DELETE
  
@Override
@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
}