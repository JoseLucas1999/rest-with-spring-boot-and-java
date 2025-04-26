package br.com.lucas.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;

//	IMPLEMENTAR	@ExceptionHandler(RequiredObjectIsNullException.class) EM CCustomEntiy
//	alterar método creae em PersonService, adicione: 
//	if(person == null) throw new RequiredObjectIsNullException();
	
	public RequiredObjectIsNullException() {
		super("Is is not allowed to persist a null object!");
	}

//	UnsupportedMathOperationException
	public RequiredObjectIsNullException(String message) {
		super(message);
	}
}
