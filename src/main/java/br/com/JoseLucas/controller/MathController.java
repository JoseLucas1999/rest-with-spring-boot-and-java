package br.com.JoseLucas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.JoseLucas.exeption.UnsupportedMathOperationException;
import br.com.JoseLucas.util.MathUtil;

@RestController
@RequestMapping("/math")
public class MathController {

	@GetMapping("/sum/{number1}/{number2}") //http://localhost:8080/math/sum/3/5
	public Double sum(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		if(!MathUtil.isNumeric(number1) || !MathUtil.isNumeric(number2)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number1) + MathUtil.convertToDouble(number2);
		return result;
	}
	
	@GetMapping("/sub/{number1}/{number2}") //http://localhost:8080/math/sub/3/5
	public Double subtraction(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		if(!MathUtil.isNumeric(number1) || !MathUtil.isNumeric(number2)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number1) - MathUtil.convertToDouble(number2);
		return result;
	}
	
	@GetMapping("/mul/{number1}/{number2}") //http://localhost:8080/math/mul/3/5
	public Double mul(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		if(!MathUtil.isNumeric(number1) || !MathUtil.isNumeric(number2)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number1) * MathUtil.convertToDouble(number2);
		return result;
	}
	
	@GetMapping("/div/{number1}/{number2}") //http://localhost:8080/math/mul/3/5
	public Double div(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		if(!MathUtil.isNumeric(number1) || !MathUtil.isNumeric(number2)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number1) / MathUtil.convertToDouble(number2);
		return result;
	}
	
	@GetMapping("/ave/{number1}/{number2}") //http://localhost:8080/math/ave/3/5
	public Double ave(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		if(!MathUtil.isNumeric(number1) || !MathUtil.isNumeric(number2)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number1) + MathUtil.convertToDouble(number2);
		result = result/2;
		return result;
	}
	
	@GetMapping("/sqrt/{number}") //http://localhost:8080/math/sqrt/3/5
	public Double sqrt(
			@PathVariable("number") String number
	) throws Exception {
		if(!MathUtil.isNumeric(number)) 
			throw new UnsupportedMathOperationException("please, set an numeric value!");
		double result = MathUtil.convertToDouble(number);
		result = Math.sqrt(result);
		return result;
	}

	//MÉTODOS
}

/*
 - Esse código é um controller simples 
 usando Spring Boot para realizar a soma de dois números enviados na URL.
 
 - 	Esse método está mapeado para a rota: /sum/{number1}/{number2}
	receberá dois parametros pela URL
	@RequestMapping("/sum/{number1}/{number2}")
	http://localhost:8080/math/sum/3/5
	
- capturam os parametros da url
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2

- Validação dos números, verifica se os dois valores são números válidos.
		if(!isNumeric(number1) || !isNumeric(number2)) throw new IllegalArgumentException();

- Converte as strings para double usando o método convertToDouble, e faz a soma
		double result = convertToDouble(number1) + convertToDouble(number2);
		return result;
 
 */


/*
@PathVariable é usada para extrair valores de variáveis 
diretamente da URL de uma requisição

@GetMapping("/{id}")

 */