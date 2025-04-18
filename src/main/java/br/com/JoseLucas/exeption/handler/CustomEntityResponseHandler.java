package br.com.JoseLucas.exeption.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.JoseLucas.exeption.ExceptionResponse;
import br.com.JoseLucas.exeption.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false)
				);
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false)
				);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}

/*Esse código é um handler global de exceções em uma aplicação Spring Boot. 
 Ele é usado para tratar qualquer exceção não capturada 
 e retornar uma resposta mais amigável e padronizada.
 */