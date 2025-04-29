package br.com.lucas.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import br.com.lucas.data.dto.v1.BookDTO;
import br.com.lucas.data.dto.v1.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface BookControllerDocs {

//	DOCUMENTAÇÃO
//----------------------------------------------------------------------------------------------------------------------------------------------------------

	@Operation(summary = "Find All Books", description = "Finds All book", tags = { "books" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	List<BookDTO> findAll();

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Operation(summary = "Finds a Book", description = "Find a specific person by your ID", tags = {
	"books" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookDTO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	BookDTO findById(Long id);

//----------------------------------------------------------------------------------------------------------------------------------------------------------			
		
	@Operation(summary = "Adds a new Book", description = "Adds a new person by passing in a JSON, XML or YML representation of the book.", tags = {
				"People" }, responses = {
						@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookDTO.class))),
						@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	BookDTO create(BookDTO book);
		
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------		
		
		@Operation(summary = "Updates a book's information", description = "Updates a person's information by passing in a JSON, XML or YML representation of the updated book.", tags = {
				"books" }, responses = {
						@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookDTO.class))),
						@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
						@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
						@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	BookDTO update(BookDTO book);
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------	
		@Operation(summary = "Delete a Book", description = "Deletes a specific book by their ID", tags = {
				"book" }, responses = {
						@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
						@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
						@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	ResponseEntity<?> delete(Long id);

}