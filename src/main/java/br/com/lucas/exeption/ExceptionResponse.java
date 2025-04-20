package br.com.lucas.exeption;

import java.util.Date;

public record ExceptionResponse(
		Date timestamp, String message, String details
		) {

}

// formata as excessões