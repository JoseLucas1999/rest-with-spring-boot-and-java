package br.com.lucas.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.model.Greeting;

@RestController
public class GreetingController {
	
	private static final String template = "hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	//http://localhost:8080/greeting?name=lucas
	//default: http://localhost:8080/greeting?name=world
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "world!") String name) {
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
