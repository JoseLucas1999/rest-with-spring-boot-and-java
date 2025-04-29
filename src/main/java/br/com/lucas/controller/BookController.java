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

import br.com.lucas.data.dto.v1.BookDTO;
import br.com.lucas.service.BookServices;

@RestController
@RequestMapping("/api/book/v1")
public class BookController implements BookControllerDocs {
	
    @Autowired
    private BookServices service;

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	//FIND ALL
	@Override
	@GetMapping(
    		produces = {MediaType.APPLICATION_JSON_VALUE, 
    					MediaType.APPLICATION_XML_VALUE,
    					MediaType.APPLICATION_YAML_VALUE
    					})
    public List<BookDTO> findAll() {
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
    public BookDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

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
    public BookDTO create(@RequestBody BookDTO book) {
        return service.create(book);
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE V2
	
  /*@PostMapping(value = "/v2",
      consumes = {MediaType.APPLICATION_JSON_VALUE, 
      			MediaType.APPLICATION_XML_VALUE,
      			MediaType.APPLICATION_YAML_VALUE
      		},
      produces = {MediaType.APPLICATION_JSON_VALUE, 
      			MediaType.APPLICATION_XML_VALUE,
      			MediaType.APPLICATION_YAML_VALUE}
  		)
  public BookDTOV2 createv2(@RequestBody BookDTOV2 book) {
  	return service.createV2(book);
  }*/

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
    public BookDTO update(@RequestBody BookDTO book) {
        return service.update(book);
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
    //DELETE

@Override
@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

	
	

}
