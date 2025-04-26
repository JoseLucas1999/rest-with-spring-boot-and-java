package br.com.lucas.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	//UnsupportedMathOperationException
	public ResourceNotFoundException(String message) {
		super(message);
	}
}

//exceção personalizada

//estende RuntimeException, ou seja, é uma exceção que pode ser lançada sem ser 
//obrigatoriamente tratada (não precisa de try-catch obrigatório).

//A anotação @ResponseStatus(HttpStatus.NOT_FOUND) indica que, 
//quando essa exceção for lançada, o Spring vai automaticamente responder para o 
//cliente (navegador, Postman, etc) com o código HTTP

//O construtor recebe uma mensagem e repassa ela para a superclasse (RuntimeException) — 
//essa mensagem vai ser o que aparece no erro.