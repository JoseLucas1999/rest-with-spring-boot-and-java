package br.com.JoseLucas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.JoseLucas.exeption.UnsupportedMathOperationException;
import br.com.JoseLucas.service.MathService;
import br.com.JoseLucas.util.MathUtil;

@RestController
@RequestMapping("/math")
public class MathController {
	
	@Autowired
	private MathService service;
	
	//toda aógica está sendo implementada pelo MathService
	@GetMapping("/sum/{number1}/{number2}") //http://localhost:8080/math/sum/3/5
	public Double sum(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		return service.sum(number1,number2);
	}
	
	@GetMapping("/sub/{number1}/{number2}") //http://localhost:8080/math/sub/3/5
	public Double subtraction(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		return service.substraction(number1, number2);
	}
	
	@GetMapping("/mul/{number1}/{number2}") //http://localhost:8080/math/mul/3/5
	public Double multiplication(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		return service.multiplication(number1, number2);
	}
	
	@GetMapping("/div/{number1}/{number2}") //http://localhost:8080/math/mul/3/5
	public Double division(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
	) throws Exception {
		return service.division(number1, number2);
	}
	
	@GetMapping("/ave/{number1}/{number2}") //http://localhost:8080/math/ave/3/5
	public Double average(
			@PathVariable("number1") String number1, 
			@PathVariable("number2") String number2
			) throws Exception {
		return service.average(number1, number2);
	}
	
	@GetMapping("/sqrt/{number}") //http://localhost:8080/math/sqrt/3/5
	public Double sqrt(@PathVariable("number") String number) throws Exception {
		return service.sqrt(number);
	}
}
// @RestController : anotação que indicaa a class como controller
// @RequestMapping("/math") : requisição global do controller
// @GetMapping : requisição via Get
// @PathVariable : recupera parametro enviado pela URL